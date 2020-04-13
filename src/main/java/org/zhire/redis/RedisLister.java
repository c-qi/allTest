package org.zhire.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: chenqi
 * @Date: 2020.4.10 19:41
 */
@Component
public class RedisLister implements MessageListener {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = (String) redisTemplate.getValueSerializer().deserialize(message.getBody());


    }
}
