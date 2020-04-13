package org.zhire.design.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 增强的代码
 *
 * @Author: chenqi
 * @Date: 2019.9.24 10:14
 */
public class ProxyHandler implements InvocationHandler {
    private Object obj;

    public ProxyHandler(Object obj) {
        this.obj = obj;
    }

    /**
     * @param proxy  代理对象的引用
     * @param method 当前执行的方法
     * @param args   当前方法所需的参数
     * @return 和被代理对象的方法具有相同的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before ...");
        Object o = method.invoke(obj, args);
        System.out.println("after ...");
        return o;
    }
}
