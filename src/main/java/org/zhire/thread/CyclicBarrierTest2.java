package org.zhire.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 让所有线程都等待完成后才会继续下一步行动
 * <p>
 * 用于多线程计算数据，最后合并计算结果的场景
 * <p>
 * CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
 * CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。
 *
 * @Author: chenqi
 * @Date: 2020.6.10 14:27
 */
public class CyclicBarrierTest2 {
    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                // await()方法让线程阻塞，最后一个到达的线程完成了Runnable里的任务再往下执行
                // ReentrantLock
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");

                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        int threadNum = 5;
        // threadNum 是参与线程的个数  Runnable 是最后一个到达线程要做的任务
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });

        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }

}
