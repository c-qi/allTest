package org.zhire.demo.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zhire.Interceptor.AopConfig;
import org.zhire.Interceptor.AopTest;

@Configuration
public class AOPConfig {
    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }
    @Bean
    public AopConfig aopConfig() {
        return new AopConfig();
    }



}
