package org.zhire.demo.guava.event;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2020/12/28 11:29
 * @Author by chenqi
 */
@Configuration
public class EventBusConfig {

    @Autowired
    private EventListener eventListener;

    @Bean
    public EventBus getEventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(eventListener);
        return eventBus;
    }

}
