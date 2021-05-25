package org.zhire.thread;

/**
 *
 * @Date 2021/5/21 11:18
 * @Author by chenqi
 */
public class SyncTest {
    public static void main(String[] args) {
        new Thread(() -> t(0)).start();
        new Thread(() -> t(1)).start();
        new Thread(() -> t(2)).start();
        new Thread(() -> t(3)).start();
        new Thread(() -> t(4)).start();
    }

    public synchronized static void t(int a) {
//        synchronized (SyncTest.class) {
        System.out.println(Thread.currentThread().getName() + " " + a);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2(a);
//        }
    }

    private static void t2(int a) {
        // synchronized (SyncTest.class) {
        System.out.println(Thread.currentThread().getName() + " " + a);
        //}
    }
}
