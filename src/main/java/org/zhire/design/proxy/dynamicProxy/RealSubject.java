package org.zhire.design.proxy.dynamicProxy;

/**
 * 真相对象
 *
 * @Author: chenqi
 * @Date: 2019.9.24 10:13
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("do something");
    }
}
