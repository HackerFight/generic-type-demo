package com.qiuguan.generic.retrymock.service;

import com.qiuguan.generic.retrymock.event.RetryEvent;
import com.qiuguan.generic.retrymock.event.WechatRetryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author  fu yuan hui
 */
@RequiredArgsConstructor
@Component
public class WechatRetryStrategy implements RetryStrategy {

    private final RetryTaskSchedule<WechatRetryEvent> wechatRetrySchedule;

    @Override
    public void retry(RetryEvent event) {
        if (event instanceof WechatRetryEvent wechatRetryEvent) {
            wechatRetrySchedule.retry(wechatRetryEvent);
        }
    }

    @Override
    public boolean supports(Class<? extends RetryEvent> eventType) {
        return WechatRetryEvent.class.isAssignableFrom(eventType);
    }
}