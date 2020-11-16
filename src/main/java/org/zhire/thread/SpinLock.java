package org.zhire.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @Author: chenqi
 * @Date: 2019.10.10 13:45
 */
@Slf4j
public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    void lock() {
        Thread thread = Thread.currentThread();
        while (!sign.compareAndSet(null, thread)) {
        }
    }

    void unlock() {
        Thread thread = Thread.currentThread();
        sign.compareAndSet(thread, null);
    }

    @Test
    public void t() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(this::testLock);
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(this::testLock);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(this::testLock);
        CompletableFuture.allOf(future, future1, future2).join();
        try {
            System.out.println("1:" + future.get());
            System.out.println("2:" + future1.get());
            System.out.println("3:" + future2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

    int i = 0;

    private String testLock() {
        log.info("lock start");
        lock();
        try {
           // Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        i++;
        unlock();
        log.info("unlock end");
        return i + "";
    }
}
