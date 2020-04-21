package org.zhire.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: chenqi
 * @Date: 2020.4.21 09:24
 */
public class ThreadTest {

    @org.junit.Test
    public void dxc() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Object> submit = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                int i = 0;
                for (; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "线程1 .....");
                    System.out.println(i);
                    Thread.sleep(1000);
                }
                return i;
            }
        });
        Future<Object> submit2 = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                int i = 5;
                for (; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "线程2 .....");
                    System.out.println(i);
                    Thread.sleep(1000);
                }
                return i;
            }
        });
        service.shutdown();

        Integer i1 = (Integer) submit.get();
        Integer i2 = (Integer) submit2.get();
        System.out.println(i1);
        System.out.println("===================================");
        System.out.println(i2);

    }

    @org.junit.Test
    public void dxc2() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.execute(new test3());
            System.out.println("************* " + i + " *************");
        }
        service.shutdown();
    }


    public class test3 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程被调用了。");
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @org.junit.Test
    public void dxc3lambda() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> submit = service.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "  One");
            return 1;
        });
        Future<Integer> submit1 = service.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "  Two");
            return 2;
        });
        System.out.println(submit.get());
        System.out.println(submit1.get());


        service.shutdown();


    }

    // ====================================================================== //
    @org.junit.Test
    public void test04() {
        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
        // 十名选手
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            final int no = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 如果当前计数为零，则此方法立即返回。
                        // 等待
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000L));
                        System.out.println(" No." + no + " arrived");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }
                }
            };
            executorService.execute(run);
        }
        System.out.println("Game start...");
        // begin减一，开始游戏
        begin.countDown();
        try {
            end.await();
            // 等待end变为0，即所有选手到达终点
            System.out.println("Game over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


    @org.junit.Test
    public void test001() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; ++i) // create and start threads
        {
            System.out.println("start " + i);
            new Thread(new Worker(startSignal, doneSignal)).start();
            System.out.println("end   " + i);
        }

        System.out.println("hello");  // don't let run yet

        System.out.println("=========== start begin ======== " + startSignal.getCount());
        startSignal.countDown();      // let all threads proceed
        // 每调用一次这个方法，在构造函数中初始化的count值就减1。
        // 所以当N个线程都调 用了这个方法，count的值等于0，
        // 然后主线程就能通过await()方法，恢复执行自己的任务。
        System.out.println("============ start end   ======= " + startSignal.getCount());

        System.out.println("=========== done begin ======== " + doneSignal.getCount());
        doneSignal.await();           // wait for all to finish
        // 调用此方法会一直阻塞当前线程，直到计时器的值为0，除非线程被中断。
        System.out.println("=========== done end   ======= " + doneSignal.getCount());

    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();

                doWork();

                doneSignal.countDown();
            } catch (Exception ex) {
            } // return;
        }

        void doWork() {
            System.out.println(Thread.currentThread().getName() + " worker ");
        }
    }
    @org.junit.Test
    public void xcc() throws InterruptedException, ExecutionException {
        // 开始时间
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 4000; i++) {
            list.add(i + "");
        }
        // 每500条数据开启一条线程
        int threadSize = 500;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadSize == 0;

        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        List<String> cutList = null;

        // 确定每条线程的数据
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(threadSize * i, dataSize);
            } else {
                cutList = list.subList(threadSize * i, threadSize * (i + 1));
            }
            // System.out.println("第" + (i + 1) + "组：" + cutList.toString());
            final List<String> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    // sms begin
                    System.out.println(Thread.currentThread().getName() + "线程：" + listStr);
                    // sms end
                    return 1;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }

        List<Future<Integer>> results = exec.invokeAll(tasks);
        // 关闭线程池
        exec.shutdown();
        System.out.println("线程任务执行结束");
        System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
    }
}
