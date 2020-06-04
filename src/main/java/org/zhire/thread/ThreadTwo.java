package org.zhire.thread;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Runtime.getRuntime().availableProcessors() 获取本机cup核心数
 * 线程池核心数量的创建分一下情况灵活设置：
 * 1）IO密集型任务
 * 可以使用稍大的线程池，一般为2*CPU核心数。
 * CPU使用率并不高，因此可以让CPU在等待IO的时候去处理别的任务，充分利用CPU时间。
 * 2）CPU密集型任务
 * 尽量使用较小的线程池，一般为CPU核心数+1。
 * 因为CPU密集型任务使得CPU使用率很高，若开过多的线程数，只能增加上下文切换的次数，因此会带来额外的开销
 *
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
                Runtime.getRuntime().availableProcessors() + 1,
                Runtime.getRuntime().availableProcessors() + 1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        System.out.println(Runtime.getRuntime().availableProcessors());
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
                System.out.println("B:" + " " + a);
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
