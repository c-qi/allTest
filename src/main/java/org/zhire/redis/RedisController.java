package org.zhire.redis;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenqi
 * @Date: 2020.4.13 16:01
 */
@Slf4j
//@RestController
@RequestMapping("/redisLister")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

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

}
