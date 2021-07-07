package org.zhire.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
public class AsyncConfig {

    /**
     * 线程数量
     */
    private static final int nThreads = Runtime.getRuntime().availableProcessors() * 2;
    private static final int MAX_POOL_SIZE = 100;
    private static final int QUEUE_CAPACITY = 1024;
    private static final int KEEP_ALIVE_SECOND = 10;


    /**
     * spring
     *
     * @return
     */
    @Bean("taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(4);
        // 设置最大线程数
        executor.setMaxPoolSize(4);
        // 设置队列容量
        executor.setQueueCapacity(10000);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(2);
        // 设置默认线程名称
        executor.setThreadNamePrefix("thread-pool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

    /**
     * jdk
     *
     * @return
     */
    @Bean(name = "commonExecutor", destroyMethod = "shutdown")
    public ThreadPoolExecutor getCommonExecutor() {
        final int corePoolSize = 3;
        final int maximumPoolSize = 3;
        final long keepAliveTime = 60;
        final TimeUnit keepAliveTimeUnit = TimeUnit.SECONDS;
        ThreadFactoryBuilder threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d");
        ThreadFactory factory = threadFactory.build();
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, keepAliveTimeUnit,
                new LinkedBlockingQueue<>(32), factory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
