package org.zhire.redis;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhire.redis.redission.DistributedLock;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenqi
 * @Date: 2020.4.13 16:01
 */
@Slf4j
@RestController
@RequestMapping("/redisLister")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DistributedLock distributedLock;

    @RequestMapping("/test")
    public String testLister() {
        Map hashMap = Maps.newHashMap();
        hashMap.put("1", 1);
        hashMap.put("2", 2);
        redisTemplate.boundValueOps("delete").set(hashMap);
        redisTemplate.boundValueOps("savedelete").set(hashMap);
        log.info("redis数据存入成功");
        Boolean delete = redisTemplate.expire("delete", 30, TimeUnit.SECONDS);
        Boolean savedelete = redisTemplate.expire("savedelete", 30, TimeUnit.SECONDS);
        log.info("过期任务创建是否成功：{}", delete);
        log.info("过期任务创建是否成功：{}", savedelete);
        return delete.toString();
    }

    @RequestMapping("/test2")
    public void setTest() {
        redisTemplate.delete("setTest");
        Long add = redisTemplate.boundSetOps("setTest").add(1, 2, 3, 4, 5, 6, 7, 8);
        log.info("add:{}", add);
        Set set = redisTemplate.boundSetOps("setTest").members();
        log.info("set:{}", set);
        // 随机弹出一个数据
        Object pop = redisTemplate.boundSetOps("setTest").pop();
        log.info("pop:{}", pop);
        Object pop2 = redisTemplate.boundSetOps("setTest").pop();
        log.info("pop2:{}", pop2);
        Object pop3 = redisTemplate.boundSetOps("setTest").pop();
        log.info("pop3:{}", pop3);
        Set set2 = redisTemplate.boundSetOps("setTest").members();
        log.info("set2:{}", set2);
    }

    @RequestMapping("/test3")
    public String testLock(@RequestParam Long id) {
        log.info("加锁id:{}", id);
        distributedLock.lockAndExec(id + "", this::doSomething);
        log.info("释放锁:{}", id);
        return "OK";
    }

    private String doSomething() {
        log.info("time:{}", System.currentTimeMillis());
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
