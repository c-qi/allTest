package org.zhire.demo.guava.event;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2020/12/28 11:29
 * @Author by chenqi
 */
@Configuration
public class EventBusConfig {

    @Bean
    public EventBus getEventBus() {
        return new EventBus();
    }

}
