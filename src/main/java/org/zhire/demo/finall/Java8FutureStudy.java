package org.zhire.demo.finall;

import org.junit.Test;

import java.util.concurrent.*;

public class Java8FutureStudy {

    @Test
    public void testFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                // dosomething() 以异步方式在新的线程中执行耗时业务
                return null;
            }
        });

        // doOtherthing(); 异步操作的同时可以干其他的事
        try {
            // 获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
            // 如果不加时间会一直阻塞等待结果
            Object o = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程在等待过程中被中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 处理异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            // 在Future对象完成之前超过已过期
        }


    }

}
