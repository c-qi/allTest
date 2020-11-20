package org.zhire.thread;


/**
 * wait(),notifyAll()必须放在synchronized里面。
 * wait是让使用wait方法的对象等待，暂时先把对象锁给让出来，给其它持有该锁的对象用，其它对象用完后再告知（notify）等待的那个对象可以继续执行了。
 * wait/notify主要用于一个线程要等待另一个线程执行完后，然后得到执行结果的情况
 *
 * @Author: chenqi
 * @Date: 2019.8.8 18:07
 */
public class ThreadWait {
    private static volatile int a = 0;
    public static Object object = new Object();

    public static void main(String[] args) {
        Thread A = new Thread(() -> {
            synchronized (object) {
                try {
                    while (a != 0) {
                        object.wait();
                    }
                    System.out.println("aaaa");
                    a = 1;
                    object.notifyAll(); // 唤醒等待的线程，锁释放后，被唤醒的线程会争夺资源
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread B = new Thread(() -> {
            synchronized (object) {
                try {
                    while (a != 1) {
                        object.wait();
                    }
                    System.out.println("bbbb");
                    a = 2;
                    object.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread C = new Thread(() -> {
            synchronized (object) {
                try {
                    while (a != 2) {
                        object.wait();
                    }
                    System.out.println("cccc");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        B.start();
        C.start();
        A.start();

    }

}
