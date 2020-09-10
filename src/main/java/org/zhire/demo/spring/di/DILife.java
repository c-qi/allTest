package org.zhire.demo.spring.di;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * bean的生命周期
 */
@Component
public class DILife implements BeanNameAware,
        BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private DIAnimal animal;

    void use() {
        animal.use();
    }

    @PostConstruct
    public void init() {
        System.out.println("【" + this.getClass().getSimpleName()
                + "】注解@PostConstruct 定义的自定义初始化方法");
    }

    @PreDestroy
    public void destroy1() {
        System.out.println("【" + this.getClass().getSimpleName()
                + "】注解@PreDestroy 定义的自定义销毁方法");
    }

    @Autowired
    @Qualifier("DICat")
    public void setBeanFactory(DIAnimal animal) throws BeansException {
        System.out.println("延迟依赖注入");
        this.animal = animal;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("【" + this.getClass().getSimpleName()
                + "】调用 BeanNameAware 的 setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【" + this.getClass().getSimpleName()
                + "】 DisposableBean 方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【" + this.getClass().getSimpleName()
                + "】调用 InitializingBean 的 afterPropertiesSet 方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【" + this.getClass().getSimpleName()
                + "】调用 ApplicationContextAware 的 setApplicationContext");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory:" + beanFactory);
    }
}
