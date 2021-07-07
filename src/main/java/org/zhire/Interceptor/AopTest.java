package org.zhire.Interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @Date 2021/6/29 6:43 下午
 * @Author chenqi
 */
@Component
public class AopTest implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String originalBody =
                methodInvocation.getArguments()[0].toString();
        System.out.println("originalBody:" + originalBody);
        Object object = methodInvocation.proceed();
        System.out.println("object:" + object);
        return object;
    }
}
