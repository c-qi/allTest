package org.zhire.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class FooBar {
 * public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 * }
 * <p>
 * public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.7.16 15:30
 */
public class ThreadTest5 {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(1);
    private static Semaphore s = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);
    public static Object object = new Object();

    public static volatile int flag = 0;

    public static class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                try {
                    // s.acquire();
                    //System.out.println("aaa");
                    printFoo.run();
                    //s2.release();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                try {
                    // s2.acquire();
                    // System.out.println("bbb");
                    printBar.run();
                    // s.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        try {
            FooBar fooBar = new FooBar(10);
//            Thread thread = new Thread();
//            Thread thread2 = new Thread();
//            fooBar.foo(thread);
//            fooBar.bar(thread2);
//            thread.start();
//            System.out.println("__------");
//            thread2.start();
//            System.out.println("__------");

            Thread t1 = new Thread(() -> {
                try {
                    fooBar.foo(() -> {
                        System.out.print("foo");
                        countDownLatch.countDown();
                        try {
                            countDownLatch2.await();
                            countDownLatch2 = new CountDownLatch(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    fooBar.bar(() -> {
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print("bar");
                        countDownLatch2.countDown();
                        countDownLatch = new CountDownLatch(1);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            t2.start();
            t1.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
