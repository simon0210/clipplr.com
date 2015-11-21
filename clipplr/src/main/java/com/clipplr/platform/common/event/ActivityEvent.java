package com.clipplr.platform.common.event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * Created by simon on 7/27/15.
 */
public class ActivityEvent extends ApplicationEvent {

    private static final long serialVersionUID = -5493649830545448267L;

    private final Date createdAt;

    public ActivityEvent(Object source) {
        super(source);
        this.createdAt = new Date(this.getTimestamp());
    }

    public ActivityEvent(Object source, Date createdAt) {
        super(source);
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
