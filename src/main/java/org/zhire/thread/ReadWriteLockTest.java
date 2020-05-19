package org.zhire.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock读写锁 默认非公平
 *
 * @Author: cq
 * @Date: 2020.05.19 09:28
 */
public class ReadWriteLockTest {

    private static final Lock readLock = new ReentrantReadWriteLock().readLock();
    private static final Lock writeLock = new ReentrantReadWriteLock().writeLock();


    public static void main(String[] args) throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                readLock.lock();
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + " AAAAAAAAAA: " + writeLock.toString());
                method("a");
                writeLock.unlock();
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " BBBBBBBBBB: " + writeLock.toString());
                method("b");
            }
        };
        thread.start();
        thread2.start();
    }


    public static void method(String a) {
        writeLock.lock();
        System.out.println(a);
        System.out.println(Thread.currentThread().getName() + " : " + writeLock.toString());
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
        writeLock.unlock();
    }
}
