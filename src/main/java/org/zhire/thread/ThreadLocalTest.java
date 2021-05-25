package org.zhire.thread;

/**
 * @Date 2021/5/24 10:58
 * @Author by chenqi
 */
public class ThreadLocalTest {

    static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            local.set("cq");
            System.out.println(local.get());
        }).start();

        new Thread(() -> {
            local.set("cq2");
            local.remove();
            System.out.println(local.get());

        }).start();


        new Thread(() -> {
            local.set("cq3");
            System.out.println(local.get());
        }).start();
    }

}
