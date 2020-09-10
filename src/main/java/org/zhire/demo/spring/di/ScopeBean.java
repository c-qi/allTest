package org.zhire.demo.spring.di;

import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zhire.demo.spring.ioc.IOCConfig;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 多例模式
public class ScopeBean {

    @Test
    public void testScope(){
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(IOCConfig.class);
        ScopeBean scopeBean = ctx.getBean(ScopeBean.class);
        ScopeBean scopeBean2 = ctx.getBean(ScopeBean.class);
        System.out.println(scopeBean == scopeBean2);


    }
}
