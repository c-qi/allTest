package org.zhire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhire.demo.test.AsyncTaskDemo;

import java.util.concurrent.*;

import static java.util.concurrent.CompletableFuture.*;

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

    @GetMapping("/test2222")
    public String testAsync332() {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            taskExecutor.execute(() -> System.out.println("id：" + Thread.currentThread().getId() + " " + finalI));
        }
        try {
            Thread.sleep(1000 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------");
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            taskExecutor.execute(() -> System.out.println("id：" + Thread.currentThread().getId() + " " + finalI));
        }
        return "ok";
    }


    @GetMapping("/test99")
    public String testAsync223(@RequestParam String id) throws Exception {
        runAsync(() -> {
            System.out.println(Thread.currentThread().getId() + " " + id);
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + " " + id);
            return 0;
        });
        return "ok";
    }

    public static void main(String[] args) {
        CompletableFuture<Void> f1 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f2 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f3 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f4 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f5 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f6 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f7 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<Void> f8 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);
        CompletableFuture<Void> f9 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);
        CompletableFuture<Void> f10 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);
        CompletableFuture<Void> f11 = runAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, executorService);
        allOf(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11).join();

    }

}
