package org.zhire.thread;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 切割数据，多线程处理数据
 *
 * @Author: chenqi
 * @Date: 2020.06.04
 */
public class ThreadTest3 {

    public static void main(String[] args) throws Exception {

        // 开始时间
        StopWatch watch = new StopWatch();
        watch.start();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 1000; i++) {
            list.add(i);
        }
        // 每200条数据开启一条线程
        int threadSize = 200;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadSize == 0;
        System.out.println(special);

        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        List<Integer> cutList = null;

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
            final List<Integer> listStr = cutList;
            task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int i = 0;

                    for (Integer integer : listStr) {
                        i = i + integer;
                    }
                    System.out.println(Thread.currentThread().getName() + "线程：" + listStr);
                    return i;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }
        Integer resultNum = 0;
        List<Future<Integer>> results = exec.invokeAll(tasks);
        for (Future<Integer> result : results) {
            resultNum += result.get();
        }
        watch.stop();
        System.out.println(watch.getTotalTimeSeconds());
        System.out.println(resultNum);
        // 关闭线程池
        exec.shutdown();

    }

}
