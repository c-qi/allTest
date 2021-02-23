//package org.zhire.redis.redission;
//
//
//import lombok.Data;
//import org.apache.commons.lang3.StringUtils;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.config.SingleServerConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Data
//public class RedissonConfig {
//
//    @Value("${spring.redis.url}")
//    private String address;
//
//    @Value("${spring.redis.password:}")
//    private String password;
//
//    @Value("${spring.redis.database:0}")
//    private String database;
//
//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        SingleServerConfig singleServerConfig = config.useSingleServer();
//        singleServerConfig.setAddress(address);
//        singleServerConfig.setDatabase(Integer.valueOf(database));
//        if (StringUtils.isNotBlank(password)) {
//            singleServerConfig.setPassword(password);
//        }
//        RedissonClient redissonClient = Redisson.create(config);
//        return redissonClient;
//    }
//
//    @Bean
//    public DistributedLock distributedLocker(RedissonClient redissonClient) {
//        DistributedLock locker = new RedissonLock(redissonClient);
//        return locker;
//    }
//
//}
