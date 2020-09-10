package org.zhire.demo.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "org.zhire.demo.spring.*", lazyInit = true)
@Configuration // 代表这是一个 Java 配置文件，Spring 的容器会根据它来生成 IoC 容器去装配 Bean
public class IOCConfig {

    @Bean(name = "getUser") // 把方法返回的 POJO 装配到 IoC 容器中，而其
    // 属性 name 定义这个 Bean 的名称，如果没有配置它，则将方法名称“getUser”作为 Bean 的名称保
    // 存到 Spring IoC 容器中。
    public IOCUser getUser() {
        IOCUser user = new IOCUser();
        user.setId(1);
        user.setName("cq");
        user.setAddress("beijing");
        return user;
    }

}
