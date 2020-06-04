package org.zhire.thread;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 两个线程，线程A不断打印1-10的数字，要求在打印到第五个数字的时候通知线程B
 *
 * @Author: chenqi
 * @Date: 2020.06.04
 */
public class ThreadTest4 {
    public static void main(String[] args) throws Exception {
        Object object = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(i);
                        if (i == 5) {
                            synchronized (object) {
                                try {
                                    object.notifyAll();
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                }
            }

        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        while (true) {
                            object.wait();
                            System.out.println("线程2执行了");
                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        });
        thread.start();
        thread2.start();

    }

}
