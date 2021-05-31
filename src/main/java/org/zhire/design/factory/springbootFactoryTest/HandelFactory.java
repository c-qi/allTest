package org.zhire.design.factory.springbootFactoryTest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂模式
 *
 * @Date 2021/5/11 18:28
 * @Author by chenqi
 */
@Slf4j
@Component
public class HandelFactory {

    private static final Map<String, Handel> map = new HashMap<>();

    static {
        map.put("1", new HandelA());
        map.put("2", new HandelB());
        map.put("3", new HandelC());
    }

    static Handel getHandel(Integer id) {
        return map.get(id.toString());
    }

    Map setMap(String key, String newKey) {
        Handel handel = map.get(key);
        map.remove(key);
        map.put(newKey, handel);
        log.info("map:{}", JSON.toJSONString(map));
        return map;
    }


}
