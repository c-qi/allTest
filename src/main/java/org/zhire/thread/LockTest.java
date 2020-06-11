package org.zhire.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Lock锁 -> AbstractQueuedSynchronizer(AQS)实现 -> CAS实现
 *
 * @Author: chenqi
 * @Date: 2019.10.12 09:28
 */
public class LockTest {
    private static final Lock lock = new ReentrantLock();

    private static final ReentrantReadWriteLock lock2 = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " AAAAAAAAAA: " + lock.toString());
                method("a");
                lock.unlock();
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " BBBBBBBBBB: " + lock.toString());
                method("b");
            }
        };
        thread.start();
        thread2.start();
    }


    public static void method(String a) {
        lock.lock();
        System.out.println(a);
        System.out.println(Thread.currentThread().getName() + " : " + lock.toString());
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
        lock.unlock();
    }
}
