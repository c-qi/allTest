package org.zhire.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理工作将后置处理器加入到容器中，
 * 如果我们需要在Spring容器完成Bean的实例化、
 * 配置和其他的初始化前后添加一些自己的逻辑处理，
 * 我们就可以定义一个或者多个BeanPostProcessor接口的实现，然后注册到容器中。
 *
 * ===Spring IOC容器实例化Bean===
 * ===调用BeanPostProcessor的postProcessBeforeInitialization方法===
 * ===调用bean实例的初始化方法===
 * ===调用BeanPostProcessor的postProcessAfterInitialization方法===
 *
 * @Date 2020/12/1 15:17
 * @Author by chenqi
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    // bean初始化方法调用前被调用
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        // System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);
        return bean;
    }

    // bean初始化方法调用后被调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        // System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
        return bean;
    }

}