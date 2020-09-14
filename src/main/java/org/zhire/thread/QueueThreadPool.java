package org.zhire.thread;

/**
 * @Author: chenqi
 * @Date: 2019.10.25 10:39
 */
public interface QueueThreadPool<T extends Runnable> {
    /**
     * 执行一个Job
     *
     * @param key
     * @param job
     * @throws InterruptedException
     */
    void execute(String key, T job) throws InterruptedException;

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取正在等待执行的任务数量
     *
     * @return
     */
    int getJobSize();

}
