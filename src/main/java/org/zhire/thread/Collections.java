package org.zhire.thread;

import java.util.*;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1、你看过那些源码吗？
 * 2、那你能讲讲 HashMap的实现原理吗？
 * 3、HashMap什么时候会进行 rehash？
 * 4、结合源码说说 HashMap在高并发场景中为什么会出现死循环？
 * 5、JDK1.8中对 HashMap做了哪些性能优化？
 * 6、HashMap和 HashTable有何不同？
 * 7、HashMap 和 ConcurrentHashMap 的区别？
 * 8、ConcurrentHashMap和LinkedHashMap有什么区别？
 * 9、为什么ConcurrentHashMap中的链表转红黑树的阀值是8？
 *
 * @Author: chenqi
 * @Date: 2019.12.3 14:13
 */
public class Collections {
    public static void main(String[] args) {
        /**
         * 1.7和1.8扩容机制不同
         */
        Map map = new HashMap();
        map.put(null, 0);
        System.out.println(map.get(null));

        /**
         * 基于hash表 安全 synchronized k-v不为空
         */
        Hashtable hashTable = new Hashtable();
        hashTable.put("!", 1);

        /**
         * 1.7 lock
         * 1.8 synchronized
         * k-v不为空
         */
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1", 1);
        /**
         *  实现HashMap 基于链表
         */
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("1", null);


        ArrayList al = new ArrayList();
        al.add(1);
        al.add(3);
        al.add(2);

        ArrayList a = new ArrayList();
        a.addAll(al);
        System.out.println(a.toString());


        CopyOnWriteArrayList copy = new CopyOnWriteArrayList();
        copy.add(null);


    }

    @Test
    public void a() {
        Thread r = new Thread() {
            @Override
            public void run() {
                try {
                    test();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread r1 = new Thread() {
            @Override
            public void run() {
                try {
                    test();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        r.start();
        r1.start();

    }

    Map map = new HashMap(2);

    void test() {

        try {
            map.put("1", 1);
            map.put("2", 2);
            map.put("3", 3);
            map.put("4", 4);
            Set set = map.keySet();
            for (Object o : set) {
                String s = map.get(o).toString();
                System.out.println(Thread.currentThread().getName() + " " + o.toString() + " : " + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
