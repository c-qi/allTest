package org.zhire.design.factory.springbootFactoryTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂模式
 *
 * @Date 2021/5/11 18:28
 * @Author by chenqi
 */
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

}
