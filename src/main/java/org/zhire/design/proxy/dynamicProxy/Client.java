package org.zhire.design.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 可以在程序运行期间根据需要动态的创建代理类及其实例来完成具体的功能
 *
 * @Author: chenqi
 * @Date: 2019.9.24 10:31
 */
public class Client {
    public static void main(String[] args) {
        // 创建真实对象
        Subject real = new RealSubject();
        // 创建真实对象的代理对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(
                // Subject.class.getClassLoader(),
                // new Class[]{Subject.class},
                real.getClass().getClassLoader(),
                real.getClass().getInterfaces(),
                new ProxyHandler(real));
        proxySubject.doSomething();
    }
}
