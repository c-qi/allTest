package org.zhire.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 监听配置
 * 此外redis的配置文件加上：notify-keyspace-events Ex
 */
@Configuration
@Slf4j
public class PubsubConfiguration {

    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private RedisLister redisLister;


    @Bean
    public ChannelTopic expiredTopic() {
        // __keyevent@0__:expired
        String channelTopic = String.format("__keyevent@%d__:expired", redisProperties.getDatabase());
        log.info("redis key过期监听channel：{}", channelTopic);
        return new ChannelTopic(channelTopic);

    }


    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(@Autowired RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(redisLister, expiredTopic());
        return redisMessageListenerContainer;
    }
}

 
