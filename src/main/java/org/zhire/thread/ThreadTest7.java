package org.zhire.thread;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * 三个不同的线程将会共用一个 Foo 实例。
 * <p>
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/23 16:53
 * @Author by chenqi
 */
public class ThreadTest7 {
    class Foo {

        final Object object = new Object();
        private volatile int i = 1;

        public Foo() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (object) {
                while (i != 1) {
                    object.wait();
                }
                printFirst.run();
                i = 2;
                object.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (object) {
                while (i != 2) {
                    object.wait();
                }
                printSecond.run();
                i = 3;
                object.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (object) {
                while (i != 3) {
                    object.wait();
                }
                printThird.run();
            }

        }
    }

    @Test
    public void test() {
        Foo foo = new Foo();
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            try {
                foo.first(() -> System.out.println("1"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            try {
                foo.second(() -> System.out.println("2"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> f3 = CompletableFuture.runAsync(() -> {
            try {
                foo.third(() -> System.out.println("3"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.allOf(f1, f2, f3).join();
    }
}
