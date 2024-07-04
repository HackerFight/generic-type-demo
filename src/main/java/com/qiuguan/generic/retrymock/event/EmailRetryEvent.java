package com.qiuguan.generic.retrymock.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fu yuan hui
 * @since 2024-06-19 13:18:05 星期三
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmailRetryEvent extends RetryEvent{

    private String emailSubject;
}
