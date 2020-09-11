package org.zhire.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器到 Spring MVC 机制，然后它会返回一个拦截器注册
        InterceptorRegistration ir = registry.addInterceptor(new MyInterceptor());
        // 指定拦截匹配模式，限制拦截器拦截请求
        ir.addPathPatterns("/interceptor/*");
    }
}