package org.zhire.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: chenqi
 * @Date: 2019.8.8 18:07
 */
public class ThreadTwo {
    private static volatile int a = 0;

    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());


        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("A:" + " " + a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().wait(1000);
                    System.out.println("B:" + " " + a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    a = 1;
                    System.out.println("C:" + " " + a);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        executor.shutdown();
    }

}
