package org.zhire.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: chenqi
 * @Date: 2020.7.30 09:10
 */
@Configuration
public class MyConfig {
    @Bean
    public ObjectB getB() {
        return new ObjectB();
    }

    @Bean
    public ObjectB getB2() {
        return new ObjectB();
    }

    @Bean
    public ObjectB getB3() {
        return new ObjectB();
    }

    @Bean
    public ObjectB getB4() {
        return new ObjectB();
    }
}
