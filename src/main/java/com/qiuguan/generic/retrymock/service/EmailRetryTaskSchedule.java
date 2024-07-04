package com.qiuguan.generic.retrymock.service;

import com.qiuguan.generic.retrymock.event.EmailRetryEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author fu yuan hui
 * @since 2024-06-28 10:06:58 星期五
 */
@Slf4j
@Component
public class EmailRetryTaskSchedule extends AbstractRetryTaskSchedule<EmailRetryEvent> {

    @Override
    public void onRetry(EmailRetryEvent event) {

    }

    @Override
    public void retry(EmailRetryEvent event) {
        log.info("****************************************调用了 EmailRetryTaskSchedule.retry() 方法");
    }
}
