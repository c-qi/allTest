package org.zhire.thread;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;
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
    @Test
    public void t() {
        System.out.println(Collections.class);
    }

    public static void main(String[] args) {
        /**
         * 1.7和1.8扩容机制不同
         */
        HashMap map = new HashMap();
        map.put(null, 0);
        map.put(1, 324);
        map.put(2, 234);
        map.put(6, 546);
        map.put(5, 865);
        map.put(Collections.class, "cq");
        map.put(String.class, String.class);
        System.out.println(JSON.toJSONString(map));
        System.out.println(map.get(Collections.class));
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
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(2);
        concurrentHashMap.put("1", 1);
        concurrentHashMap.put("2", 1);
        concurrentHashMap.put("3", 1);
        concurrentHashMap.get("1");
        /**
         *  实现HashMap 基于链表
         */
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(null, 0);
        linkedHashMap.put(1, 324);
        linkedHashMap.put(2, 234);
        linkedHashMap.put(6, 546);
        linkedHashMap.put(5, 865);
        System.out.println(linkedHashMap);

        /**
         * ArrayList基于数组，初始化容量10，
         * add元素的时候先判断数组容量够不够装，装不下扩容原来的1.5倍，10 ---> 15
         * 然后把旧数据复制到新的数组里面(Arrays.copyOf())
         * 最后把新数据追加到数组里面
         * 线程不安全
         */
        ArrayList al = new ArrayList();
        al.add(1);
        al.add(3);
        al.add(2);

        ArrayList a = new ArrayList();
        a.addAll(al);
        System.out.println(a.toString());

        /**
         * LinkedList基于链表初始化容量0
         */
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);


        /**
         * 线程安全 ReentrantLock
         * CopyOnWriteArrayList 类的所有可变操作（add，set 等等）都是通过创建底层数组的新副本来实现的。
         * 当 List 需要被修改的时候，我并不修改原有内容，而是对原有数据进行一次复制，将修改的内容写入副本。
         * 写完之后，再将修改完的副本替换原来的数据，这样就可以保证写操作不会影响读操作了。
         *
         * 读取操作没有任何同步控制和锁操作，理由就是内部数组 array 不会发生修改，只会被另外一个 array 替换，因此可以保证数据安全。
         */
        CopyOnWriteArrayList copy = new CopyOnWriteArrayList();
        copy.add(null);

        /**
         * HashSet基于hashMap 值重复覆盖 无序
         */
        HashSet set = new HashSet();
        set.add("cq");
        set.add("cq");
        set.add("cq");
        set.add("cq");
        Optional first = set.stream().findFirst();
        if (first.isPresent())
            System.out.println("ffff:" + first.get());
        System.out.println(set);


        /**
         * LinkedHashSet基于LinkedHashMap 值重复覆盖 有序
         */
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(1);
        linkedHashSet.add(3);
        linkedHashSet.add(3);
        linkedHashSet.add(2);
        System.out.println(linkedHashSet);


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

    @Test
    public void testListAdd() {
        int oldCapacity = 10;
        int a = oldCapacity >> 1;
        System.out.println(a);
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);

        int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println(MAXIMUM_CAPACITY);
    }
}
