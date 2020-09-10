package org.zhire.demo.spring.di;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.zhire.demo.spring.ioc.IOCConfig;

@Slf4j
public class DITest {
    public static void main(String[] args) {
        // 获取我们刚才注入的IOCUser对象
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(IOCConfig.class);
        DILife diLife = ctx.getBean(DILife.class);
        diLife.use();
        // 关闭容器
        ctx.close();
    }
}
