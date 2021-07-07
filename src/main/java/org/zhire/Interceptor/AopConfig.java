package org.zhire.Interceptor;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Date 2021/6/29 9:26 下午
 * @Author chenqi
 */
//@Aspect
public class AopConfig {

    @Pointcut("execution(* org.zhire.controller..*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("-------before--------");

        Object[] args = point.getArgs();
        System.out.println(args.length);
        for (Object arg : args) {
            System.out.println("args: " + arg);
        }
        System.out.println("args0: " + args[0]);
        Object proceed = point.proceed();
        System.out.println("-------after-------");
        return proceed;
    }


}
