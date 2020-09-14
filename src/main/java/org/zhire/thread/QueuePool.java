package org.zhire.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列池
 * 当前队列池适合需要异步、并发、按顺序处理的业务
 * 通过workerNum 初始化并发队列线程数，每个队列都有一个子线程消费队列任务
 * 获取任务队列索引=Math.abs(key.hashCode()) % workerNum
 */
@Slf4j
public class QueuePool<T extends Runnable> implements QueueThreadPool<T> {

    /**
     * 队列池默认的数量
     */
    private static final int DEFAULT_WORKER_NUMBERS = Runtime.getRuntime().availableProcessors();

    /**
     * 队列池最大限制数
     */
    private static final int MAX_WORKER_NUMBERS = 12;

    /**
     * 队列池最小的数量
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 队列大小
     */
    private int queueSize = 100;

    /**
     * 工作者列表
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    /**
     * 线程编号生成
     */
    private AtomicInteger threadNum = new AtomicInteger();

    public QueuePool() {
        int worksNum = DEFAULT_WORKER_NUMBERS > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : DEFAULT_WORKER_NUMBERS;
        initializeWorkers(worksNum);
    }

    /**
     * 工作线程名称
     */
    private String threadName = "ThreadPool-Worker-";

    public QueuePool(int queueSize, String threadName) {
        this.queueSize = queueSize;
        this.threadName = threadName;
        int worksNum = DEFAULT_WORKER_NUMBERS > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : DEFAULT_WORKER_NUMBERS;
        initializeWorkers(worksNum);
    }

    public QueuePool(int workerNum, int queueSize, String threadName) {
        this.queueSize = queueSize;
        this.threadName = threadName;
        workerNum = workerNum > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : workerNum;
        workerNum = workerNum < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : workerNum;
        initializeWorkers(workerNum);
    }

    @Override
    public void execute(String key, T job) throws InterruptedException {
        fetchWorker(key).onData(job);
    }

    /**
     * 获取工作线程
     *
     * @param key
     * @return
     */
    public Worker fetchWorker(String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key 不能为空");
        }
        return workers.get(getIndex(key));
    }

    /**
     * 获取索引
     *
     * @param key
     * @return
     */
    public int getIndex(String key) {
        return Math.abs(key.hashCode()) % workers.size();
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public int getJobSize() {
        return workers.size();
    }

    /**
     * 初始化线程工作者
     *
     * @param num
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            String tn = this.threadName + threadNum.incrementAndGet();
            Thread thread = new Thread(worker, tn);
            thread.start();
            log.info("[阻塞队列 池] 初始化 [{}] 线程工作者", tn);
        }

    }

    /**
     * 设置每个队列大小
     *
     * @param queueSize
     */
    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    /**
     * 工作者，负责消费任务
     */
    class Worker implements Runnable {

        private BlockingQueue<T> blockingQueue;

        /**
         * 是否工作
         */
        private volatile boolean running = true;

        public Worker() {
            log.info("initialize blockingQueue size: {}", queueSize);
            this.blockingQueue = new LinkedBlockingQueue(queueSize);
        }


        /**
         * 添加任务
         *
         * @param job
         * @throws InterruptedException
         */
        public void onData(T job) throws InterruptedException {
            try {
                this.blockingQueue.put(job);
            } catch (Exception e) {
                log.error("Worker [{}] onData Error:{}", getClass(), e.getMessage(), e);
                throw e;
            }
        }

        @Override
        public void run() {
            while (running) {

                T job = null;
                try {
                    job = this.blockingQueue.take();
                } catch (Exception e) {
                    log.error("Worker [{}] take Error:{}", getClass(), e.getMessage(), e);
                }

                try {
                    if (job != null) {
                        job.run();
                    }
                } catch (Exception e) {
                    log.error("Worker [{}] run Error:{}", getClass(), e.getMessage(), e);
                }
            }
        }

        public void shutdown() {
            this.running = false;
        }
    }
}
