package org.zhire.config;

//import fun.chenqi.filter.LoginFiter;
import org.zhire.filter.ParamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: chenqi
 * @Date: 2019.3.25 17:39
 */
//@Configuration
public class TestFilterConfig {
    @Bean
    public FilterRegistrationBean someFilterRegistration1() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        //registration.setFilter(new LoginFiter());
        registration.setFilter(new ParamFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        return registration;
    }


}
