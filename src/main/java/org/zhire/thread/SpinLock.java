package org.zhire.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @Author: chenqi
 * @Date: 2019.10.10 13:45
 */
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

}
