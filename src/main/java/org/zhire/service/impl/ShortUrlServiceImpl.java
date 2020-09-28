package org.zhire.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.zhire.service.ShortUrlService;
import org.zhire.utils.NumberChangeUtils;

@Slf4j
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * @param oldUrl
     * @return
     */
    @Override
    public String generateShortUrl(String oldUrl) {
        String result = "";
        String shortUrlIndex = "";
        String shortUrlKey = "shorturl";
        String startUrl = "http://u.com";
        shortUrlIndex = stringRedisTemplate.opsForValue().get(shortUrlKey);
        log.info("shortUrlIndex:{}", shortUrlIndex);
        // 查询MYSQL是否存在 ...
        if (false) {
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
        return result;
    }
}
