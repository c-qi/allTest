package org.zhire.redis;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zhire.SpringBootStart;

import java.util.concurrent.*;

/**
 * @Author: chenqi
 * @Date: 2019.3.28 15:44
 */
@SpringBootTest(classes = SpringBootStart.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d").build();
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            4,
            4,
            1000L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            threadFactory,
            new CustomRejectedExecutionHandler());


    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 高并发访问Redis
     */
    @Test
    public void test() {
        for (long i = 10000000000L; i < 10000200000L; i++) {
            long finalI = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " " + finalI + " "
                            + redisTemplate.boundHashOps("testRedis").get(finalI));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

    @Test
    public void tes2t() {
        for (long i = 10000000000L; i < 10000900000L; i++) {
            redisTemplate.boundHashOps("testRedis").put(i, i);
        }
    }

}
