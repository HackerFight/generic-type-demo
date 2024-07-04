package com.qiuguan.generic.retrymock.service;

import com.qiuguan.generic.retrymock.event.RetryEvent;

/**
 * @author fu yuan hui
 *
 */
public interface RetryStrategy {

    void retry(RetryEvent event);


    boolean supports(Class<? extends RetryEvent> eventType) ;
}