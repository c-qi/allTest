package org.zhire.redis;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Author: chenqi
 * @Date: 2020.4.10 19:41
 */
@Slf4j
@Component
public class RedisLister implements MessageListener {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = (String) redisTemplate.getValueSerializer().deserialize(message.getBody());
        log.info("检测到过期的key：{}", key);
        if (key.contains("delete")) {
            ThreadFactoryBuilder threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d");
            ThreadFactory factory = threadFactory.build();
            ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors(),
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1024),
                    factory,
                    new ThreadPoolExecutor.AbortPolicy()
            );
            executorService.execute(() -> doSomething(key));
        }
    }

    private void doSomething(String k) {
        log.info("监听到有过期任务，执行业务...");
    }

}
