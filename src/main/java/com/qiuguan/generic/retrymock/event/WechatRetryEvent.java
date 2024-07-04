package com.qiuguan.generic.retrymock.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fu yuan hui
 * @since 2024-06-19 13:19:39 星期三
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatRetryEvent extends RetryEvent {

    private String wxSubject;

    /**
     * "A" / "O"
     */
    private String status;
}
