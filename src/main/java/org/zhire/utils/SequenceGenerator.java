package org.zhire.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 自增序列生成器
 */
@Component
public class SequenceGenerator {
    private static final Integer DEFAULT_LENGTH = 10;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 2020091400000000001
     *
     * @param length
     * @return
     */
    public String generate(int length) {
        String formatDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
        String key = "vpn:user_id_sequence:" + formatDate;
        Integer l = redisTemplate.opsForValue().increment(key, 1).intValue();
        redisTemplate.expire(key, 24, TimeUnit.HOURS); // 设置过期时间 24H
        String seqStr = getSequence(l, length);
        return formatDate + seqStr;
    }

    public static String getSequence(long seq, Integer length) {
        if (length == null || length <= 0) {
            length = DEFAULT_LENGTH;
        }
        String str = String.valueOf(seq);
        int len = str.length();
        if (len >= length) {
            return str;
        }
        int rest = length - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }

    public static void main(String[] args) {

        //System.out.println(getSequence(9, 11));
    }
}
