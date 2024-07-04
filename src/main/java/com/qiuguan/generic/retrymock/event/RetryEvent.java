package com.qiuguan.generic.retrymock.event;

import lombok.Getter;
import lombok.Setter;

/**
 * @author fu yuan hui
 * @since 2024-06-19 13:19:45 星期三
 */
@Setter
@Getter
public abstract class RetryEvent {

    protected String fileUrl;

    protected String fileName;

    protected Throwable throwable;

    protected String ossPath;
}
