package org.zhire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class AsyncConfig {

    /**
     * 线程数量
     */
    private static final int nThreads = Runtime.getRuntime().availableProcessors() * 2;
    private static final int MAX_POOL_SIZE = 100;
    private static final int QUEUE_CAPACITY = 1024;
    private static final int KEEP_ALIVE_SECOND = 300;


    @Bean("taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(nThreads);
        // 设置最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 设置队列容量
        executor.setQueueCapacity(QUEUE_CAPACITY);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECOND);
        // 设置默认线程名称
        executor.setThreadNamePrefix("thread-pool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
