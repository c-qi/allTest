package org.zhire.demo.spring.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class IOCTest {
    public static void main(String[] args) {
        // 获取我们刚才注入的IOCUser对象
        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(IOCConfig.class);
        IOCUser user = ctx.getBean(IOCUser.class);
        log.info("id:{}", user.getId());
        log.info("name:{}", user.getName());
    }
}
