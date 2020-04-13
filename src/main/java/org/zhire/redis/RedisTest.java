package org.zhire.redis;

import org.zhire.SpringBootStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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

    @Test
    public void test(){
        redisTemplate.boundHashOps("testRedis").put("name","chenqi");

        System.out.println(redisTemplate.boundHashOps("testRedis").get("name"));

    }


}
