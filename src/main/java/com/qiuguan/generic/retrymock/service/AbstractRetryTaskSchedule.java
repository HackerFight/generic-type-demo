package com.qiuguan.generic.retrymock.service;


import com.qiuguan.generic.retrymock.event.RetryEvent;
import org.springframework.boot.CommandLineRunner;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fu yuan hui
 * @since 2024-06-28 10:10:02 星期五
 */
public abstract class AbstractRetryTaskSchedule<T extends RetryEvent> implements RetryTaskSchedule<T>, CommandLineRunner {

    protected final Map<Integer, String> rocketMqDelayParamMap = new HashMap<>();


    @Override
    public void run(String... args) throws Exception {
        this.rocketMqDelayParamMap.put(1, "1秒");
        this.rocketMqDelayParamMap.put(2, "5秒");
        this.rocketMqDelayParamMap.put(3, "10秒");
        this.rocketMqDelayParamMap.put(4, "30秒");
        this.rocketMqDelayParamMap.put(5, "1分钟");
        this.rocketMqDelayParamMap.put(6, "2分钟");
        this.rocketMqDelayParamMap.put(7, "3分钟");
        this.rocketMqDelayParamMap.put(8, "4分钟");
        this.rocketMqDelayParamMap.put(9, "5分钟");
        this.rocketMqDelayParamMap.put(10, "6分钟");
        this.rocketMqDelayParamMap.put(11, "7分钟");
        this.rocketMqDelayParamMap.put(12, "8分钟");
        this.rocketMqDelayParamMap.put(13, "9分钟");
        this.rocketMqDelayParamMap.put(14, "10分钟");
        this.rocketMqDelayParamMap.put(15, "20分钟");
        this.rocketMqDelayParamMap.put(16, "30分钟");
        this.rocketMqDelayParamMap.put(17, "1小时");
        this.rocketMqDelayParamMap.put(18, "2小时");
    }
}
