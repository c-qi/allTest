package org.zhire.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zhire.annotation.UserMethodArgumentResolver;

import java.util.List;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器到 Spring MVC 机制，然后它会返回一个拦截器注册
        InterceptorRegistration ir = registry.addInterceptor(myInterceptor);
        // 指定拦截匹配模式，限制拦截器拦截请求
        ir.addPathPatterns("/interceptor/*")
                .addPathPatterns("/token/get")
                .excludePathPatterns("/token/login");
    }

    /**
     * 自定义@User注解获取用户信息
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserMethodArgumentResolver());
    }
}