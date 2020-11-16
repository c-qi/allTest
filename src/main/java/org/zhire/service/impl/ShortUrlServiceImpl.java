package org.zhire.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.zhire.service.ShortUrlService;
import org.zhire.utils.NumberChangeUtils;
import org.zhire.utils.RedisUtil;

@Slf4j
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取加锁结果
     *
     * @return
     */
    private boolean getRedisLockResult() {
        return redisUtil.lock("lock-test", 1000 * 60 * 2);
    }


    /**
     * 自旋获取锁结果
     *
     * @param oldUrl
     * @return
     */
    @Override
    public String generateShortUrl(String oldUrl) {
        if (!getRedisLockResult()) {
            while (true) {
                if (getRedisLockResult()) {
                    return generateShortUrlMethod(oldUrl);
                }
            }
        } else {
            return generateShortUrlMethod(oldUrl);
        }
    }

    /**
     * 生成短链
     *
     * @param oldUrl
     * @return
     */
    private String generateShortUrlMethod(String oldUrl) {
        String result = "";
        String shortUrlIndex = "";
        String shortUrlKey = "shorturl";
        String startUrl = "http://u.com/";
        shortUrlIndex = stringRedisTemplate.opsForValue().get(shortUrlKey);
        log.info("shortUrlIndex:{}", shortUrlIndex);
        // 查询MYSQL是否存在 ...
        if (true) {
            if (StringUtils.isEmpty(shortUrlIndex)) {
                shortUrlIndex = "1";
                stringRedisTemplate.opsForValue().set(shortUrlKey, shortUrlIndex);
            } else {
                shortUrlIndex = NumberChangeUtils.getNextNum(shortUrlIndex);
                stringRedisTemplate.opsForValue().set(shortUrlKey, shortUrlIndex);
            }
            result = startUrl + shortUrlIndex;
            // 入库
        }
        log.info("result:{}", result);
        redisUtil.deleteLock("lock-test");
        return result;
    }
}
