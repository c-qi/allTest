package org.zhire.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: chenqi
 * @Date: 2020.6.29 16:59
 */
public class CurrentHashMapTest {
    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(3);
        Map hashMap = new ConcurrentHashMap(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1111");
                for (int i = 0; i < 10000; i++) {
                    hashMap.put(0, "00001");
                }
                System.out.println("1111");
                count.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("222 " + hashMap.get(0));
                hashMap.put(1, "000002");
                System.out.println("222 " + hashMap.get(0));
                count.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("333 " + hashMap.get(0));
                hashMap.put(2, "00003");
                System.out.println("333 " + hashMap.get(0));
                count.countDown();
            }
        }).start();
        try {
            count.await();
            System.out.println(hashMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
