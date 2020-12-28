package org.zhire.demo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态代理
 *
 * @Author: chenqi
 * @Date: 2020.5.7 16:29
 */
public class ArrayListProxy {
    public static void main(String[] args) {
        List list = new ArrayList();
        List proxyInstance = (List) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(list, args);
                    }
                });
        proxyInstance.add("hello");
        System.out.println(list);
    }
}
