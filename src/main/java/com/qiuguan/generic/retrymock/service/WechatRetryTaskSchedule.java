package com.qiuguan.generic.retrymock.service;

import com.qiuguan.generic.retrymock.event.WechatRetryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author fu yuan hui
 * @since 2024-06-28 10:01:16 星期五
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class WechatRetryTaskSchedule extends AbstractRetryTaskSchedule<WechatRetryEvent> {



    @Override
    public void retry(WechatRetryEvent event) {
        log.info("****************************************调用了 WechatRetryTaskSchedule.retry() 方法");
    }

    @Override
    public void onRetry(WechatRetryEvent event) {

    }

}
