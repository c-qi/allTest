package org.zhire.thread;

/**
 * @Author: chenqi
 * @Date: 2019.8.9 09:33
 */
public class ThreadTest {

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
        // thread.setDaemon(true); // 守护线程
        // 主线程一旦运行结束，它就会随之结束，不管运行没运行完毕
          thread.start();
        //thread.yield(); // 线程让步 短暂放弃CPU执行权 但是还是会和其他线程抢 不会释放锁
        try {
            thread.join(); // 执行完再执行main线程
        } catch (Exception e) {
            e.printStackTrace();
        }

        int j = 0;
        while (j++ < 10) {
            System.out.println(Thread.currentThread().getName() + " : " + j);
        }

    }

}
