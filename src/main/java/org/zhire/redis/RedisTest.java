package org.zhire.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zhire.SpringBootStart;
import org.zhire.service.ShortUrlService;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author: chenqi
 * @Date: 2019.3.28 15:44
 */
@Slf4j
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
        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent("ifabsent", "1", 10, TimeUnit.MINUTES);
        // 不存在 创建 成功 true ，存在 创建 失败 false
        System.out.println(ifAbsent);
        // 不会改变上面的值
        Boolean ifAbsent2 = redisTemplate.opsForValue().setIfAbsent("ifabsent", "2", 10, TimeUnit.MINUTES);
        System.out.println(ifAbsent2);
        System.out.println(redisTemplate.opsForValue().get("ifabsent"));
    }

    @Test
    public void test3() {
        redisTemplate.opsForSet().add("setValue", "A", "B", "C", "B", "D", "E", "F");
        // 无序
        Set set = redisTemplate.opsForSet().members("setValue");
        System.out.println(JSON.toJSONString(set));
        // 随机弹出数据
        Object popValue = redisTemplate.opsForSet().pop("setValue");
        System.out.print("通过pop(K key)方法弹出变量中的元素:" + popValue);
        Object popValue2 = redisTemplate.opsForSet().pop("setValue");
        System.out.print("通过pop(K key)方法弹出变量中的元素:" + popValue2);
        Set set2 = redisTemplate.opsForSet().members("setValue");
        System.out.println(JSON.toJSONString(set2));
    }

    @Autowired
    private ShortUrlService shortUrlService;

    @Test
    public void test23() {
        for (int i = 0; i < 100; i++) {
            CompletableFuture<String> async1 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async2 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async3 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async4 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async5 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async6 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async7 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async8 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async9 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture<String> async10 = CompletableFuture.supplyAsync(() -> shortUrlService.generateShortUrl("http://zhi.re"));
            CompletableFuture.allOf(async1, async2, async3, async4, async5, async6, async7, async8, async9, async10).join();
            try {
                log.info("1 " + async1.get());
                log.info("2 " + async2.get());
                log.info("3 " + async3.get());
                log.info("4 " + async4.get());
                log.info("5 " + async5.get());
                log.info("6 " + async6.get());
                log.info("7 " + async7.get());
                log.info("8 " + async8.get());
                log.info("9 " + async9.get());
                log.info("10 " + async10.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}

