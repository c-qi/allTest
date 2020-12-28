package org.zhire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhire.demo.test.AsyncTaskDemo;

import java.util.concurrent.Future;

/**
 * 注解 @Async 可以异步处理任务 不用创建多线程 使用的时候启动类加 @EnableAsync 异步方法加 @Async
 * 注意 调用者不可以和异步方法在一个类 否则注解作用失效
 *
 * @Author: chenqi
 * @Date: 2020.6.3 16:16
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    @Autowired
    private AsyncTaskDemo asyncTaskDemo;

    @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor taskExecutor;

    @GetMapping("/test")
    public String testAsync() throws Exception {
        System.out.println("执行主方法");
        Future<String> future = asyncTaskDemo.async01(); // 有返回值 会等到程序执行完才向下执行 报错的话如果没有处理不会往下走
        System.out.println(future.get());
        asyncTaskDemo.async02(); // 无返回值 异步执行
        asyncTaskDemo.async03(); // 无返回值 异步执行
        return "ok";
    }


    @GetMapping("/test2")
    public String testAsync2() throws Exception {
        taskExecutor.execute(new MyAsync());
        return "ok";
    }

    @GetMapping("/test3")
    public String testAsync3() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "ok";
    }


    class MyAsync implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("ok");
        }
    }


}
