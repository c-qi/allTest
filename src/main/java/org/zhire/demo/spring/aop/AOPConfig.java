package org.zhire.demo.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AOPConfig {
    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }
}
