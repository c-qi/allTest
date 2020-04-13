package org.zhire.thread;


/**
 * @Author: chenqi
 * @Date: 2019.8.8 18:07
 */
public class ThreadOne implements Runnable {

    int i = 0;
    Object object = new Object();

    @Override
    public synchronized void run() {
        //synchronized (object) {
        while (true) {

            if (i > 50) {
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
        //  }


    }
}
