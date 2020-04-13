package org.zhire.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: chenqi
 * @Date: 2019.10.12 09:28
 */
public class PrintTest {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                  System.out.print(" A ");
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.print(" B ");
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                    System.out.print(" C ");
            }
        };

        thread.start();
        thread2.start();
        thread3.start();




    }


}
