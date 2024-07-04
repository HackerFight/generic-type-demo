package com.qiuguan.generic.retrymock;

import com.alibaba.fastjson2.JSONObject;
import com.qiuguan.generic.retrymock.event.EmailRetryEvent;
import com.qiuguan.generic.retrymock.event.RetryEvent;
import com.qiuguan.generic.retrymock.event.WechatRetryEvent;
import com.qiuguan.generic.retrymock.service.RetryStrategy;
import com.qiuguan.generic.retrymock.service.RetryTaskSchedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author fu yuan hui
 * @since 2024-07-02 13:21:57 星期二
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class RetryTaskListener implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {

    private final Collection<RetryStrategy> retryStrategies;

    //可以注入进来，但是我不知道怎么用
    private final Collection<RetryTaskSchedule<? extends RetryEvent>> retryTaskSchedules;


    //Spring容器注入：实际上注入不进来，集合为empty集合
    private final Collection<RetryTaskSchedule<RetryEvent>> retryTaskSchedules2;


    //手动注入
    private final Collection<RetryTaskSchedule<RetryEvent>> retryTaskSchedulesList = new ArrayList<>();

    public void onMessage(JSONObject message) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>MQ消费的消息数据: {}", message);
        if(message == null) {
            return;
        }

        String fromType = message.getString("fromType");
        RetryEvent retryEvent;
        if ("WECHAT".equalsIgnoreCase(fromType) || fromType.contains("WECHAT")) {
            retryEvent = new WechatRetryEvent();
        } else {
            retryEvent = new EmailRetryEvent();
        }


        //方法一：看着好像没有问题，但是实际上这种方式无法注入RetryTaskSchedule的，因为没有对象类型是RetryTaskSchedule<RetryEvent>的
        // retryTaskSchedules2 集合是空的
        {
            for (RetryTaskSchedule<RetryEvent> retryTaskSchedule : retryTaskSchedules2) {
                log.info(">>>>>>>>>>>>>>>>>>>>>方式一注入retryTaskSchedule = {}", retryTaskSchedule);
                retryTaskSchedule.retry(retryEvent);
            }
        }


        //方法二：
        {
            //调用 retryTaskSchedule.retry(retryEvent); 方法报错：Required type: capture of ? extends RetryEvent， Provided:RetryEvent
            //直接 new WechatRetryEvent() 也不行，提示的也是一样的错误
            for (RetryTaskSchedule<? extends RetryEvent> retryTaskSchedule : this.retryTaskSchedules) {
                //retryTaskSchedule.retry(retryEvent);
                log.info(">>>>>>>>>>>>>>>>>>> 方式二注入retryTaskSchedule = {}", retryTaskSchedule);
            }
        }


        //方式三：手动注入
        {
            for (RetryTaskSchedule<RetryEvent> retryTaskSchedule : this.retryTaskSchedulesList) {
                log.info(">>>>>>>>>>>>>>>>>>> 方式三(手动)注入retryTaskSchedule = {}", retryTaskSchedule);
                Type type = ((ParameterizedType) retryTaskSchedule.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                if(type.equals(retryEvent.getClass())) {
                    retryTaskSchedule.retry(retryEvent);
                }
            }
        }


        //方法四：利用策略模式进行中转，我其实不愿意用这种方式
        {
            try {
                for (RetryStrategy strategy : retryStrategies) {
                    if (strategy.supports(retryEvent.getClass())) {
                        strategy.retry(retryEvent);
                    }
                }
            } catch (Exception e) {
                log.error(">>>>>>>>>>>>>>>>>>消息消费失败", e);
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>开始Mock消息监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        JSONObject jsonObject = new JSONObject();
        boolean match = ThreadLocalRandom.current().nextBoolean();
        jsonObject.put("fromType", match ? "WECHAT" : "EMAIL");

        onMessage(jsonObject);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBeansOfType(RetryTaskSchedule.class).forEach((k, v) -> this.retryTaskSchedulesList.add(v));
    }
}
