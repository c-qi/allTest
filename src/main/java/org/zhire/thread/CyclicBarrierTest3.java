package org.zhire.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier默认的构造方法是CyclicBarrier（int parties），
 * 其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。
 *
 * @Author: chenqi
 * @Date: 2020.9.7
 */
public class CyclicBarrierTest3 {
    private static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {
        }
        System.out.println(2);
    }
}