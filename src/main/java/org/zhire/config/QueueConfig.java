package org.zhire.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zhire.thread.QueuePool;

@Slf4j
@Configuration
public class QueueConfig {
    @Bean("test")
    public QueuePool test() {
        return new QueuePool(6, 5, "test_");
    }
}
