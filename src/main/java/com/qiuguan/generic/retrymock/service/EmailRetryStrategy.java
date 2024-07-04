package com.qiuguan.generic.retrymock.service;

import com.qiuguan.generic.retrymock.event.EmailRetryEvent;
import com.qiuguan.generic.retrymock.event.RetryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * @author fu yuan hui
 */
@RequiredArgsConstructor
@Component
public class EmailRetryStrategy implements RetryStrategy {

    private final RetryTaskSchedule<EmailRetryEvent> emailRetrySchedule;

    @Override
    public void retry(RetryEvent event) {
        if (event instanceof EmailRetryEvent) {
            emailRetrySchedule.retry((EmailRetryEvent) event);
        }
    }

    @Override
    public boolean supports(Class<? extends RetryEvent> eventType) {
        return EmailRetryEvent.class.isAssignableFrom(eventType);
    }
}