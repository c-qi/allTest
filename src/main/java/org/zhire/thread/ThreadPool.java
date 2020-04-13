package org.zhire.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Author: chenqi
 * @Date: 2019.10.25 10:39
 */
public class ThreadPool {
    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();
        ExecutorService executorService3 = Executors.newFixedThreadPool(2);
        ThreadFactoryBuilder threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d");
        ThreadFactory factory = threadFactory.build();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2,
                2,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                // Executors.defaultThreadFactory(),
                factory,
                new ThreadPoolExecutor.AbortPolicy()
        );

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " aaa");
                for (int i = 1; i < 100; i++) {
                    boolean b = String.valueOf(i).endsWith("0");
                    if (!b) {
                        map.put(i, i);
                        //System.out.println(Thread.currentThread().getName() + JSON.toJSONString(map));
                    } else {
                        try {
                            //  System.out.println(Thread.currentThread().getName() + JSON.toJSONString(map));
                            map.remove(i - 1);
                            // System.out.println(Thread.currentThread().getName() + JSON.toJSONString(map));
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    // System.out.println(Thread.currentThread().getName() + " aaa");
                    System.out.println(Thread.currentThread().getName() + "before: " + JSON.toJSONString(map));
                    if (!map.isEmpty()) {
                        map.clear();
                    }
                    System.out.println(Thread.currentThread().getName() + "after: " + JSON.toJSONString(map));
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

//        executorService.submit(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                System.out.println(Thread.currentThread().getName() + " bbb");
//                return "2222";
//            }
//        });

        executorService.shutdown();


////////////
        Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        // scheduledExecutorService.scheduleWithFixedDelay();
        Executors.newSingleThreadExecutor();
        //  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

    }
}
