package org.zhire.demo.spring.SpringEventAndListener;

import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {

    private Task task;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public Event(Object source, Task task) {
        super(source);
        this.task = task;
    }
    public Task getTask() {
        return task;
    }
}
