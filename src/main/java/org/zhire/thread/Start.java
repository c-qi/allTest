package org.zhire.thread;

/**
 * @Author: chenqi
 * @Date: 2019.8.8 18:10
 */
public class Start {
    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        Thread thread1 = new Thread(threadOne);
        Thread thread2 = new Thread(threadOne);
        Thread thread3 = new Thread(threadOne);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
