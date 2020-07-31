package org.zhire.thread;

/**
 * 多线程输出0-1000
 *
 * @Author: chenqi
 * @Date: 2020.7.16 15:30
 */
public class ThreadTest6 {

    private static volatile int n = 1000;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    if (n < 0) {
                        break;
                    }
                    print(n--);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    if (n < 0) {
                        break;
                    }
                    print(n--);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    if (n < 0) {
                        break;
                    }
                    print(n--);

                }
            }
        }).start();
    }

    private static void print(int a) {
        System.out.println(Thread.currentThread().getName() + " " + a);
    }


}
