package org.zhire.thread;

/**
 * @Author: chenqi
 * @Date: 2019.8.9 09:33
 */
public class ThreadTest2 {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            int i = 0;
            @Override
            public void run() {
                while (i++ < 1000) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }
            }
        };

        thread.start();

        int j = 0;
        while (j++ < 10) {
            System.out.println(Thread.currentThread().getName() + " : " + j);
        }

    }

}
