package org.zhire.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore可以用于做流量控制，特别是公用资源有限的应用场景，比如数据库连接。
 * 假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程
 * 并发地读取，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，
 * 这时我们必须控制只有10个线程同时获取数据库连接保存数据，
 * 否则会报错无法获取数据库连接。这个时候，就可以使用Semaphore来做流量控制。
 *
 * @Author: chenqi
 * @Date: 2019.12.20 14:50
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    // NonfairSync非公平锁实现
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // CAS
                        s.acquire();
                        System.out.println("save data ");
                        Thread.sleep(3000);
                        // CAS
                        s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
