package com.qiuguan.generic.retrymock.service;

import com.qiuguan.generic.retrymock.event.RetryEvent;

/**
 * @author fu yuan hui
 * @since 2024-06-19 13:17:52 星期三
 */
public interface RetryTaskSchedule<T extends RetryEvent> {

    /**
     * 准备重试
     * @param event
     */
    void onRetry(T event);

    void retry(T event);
}
