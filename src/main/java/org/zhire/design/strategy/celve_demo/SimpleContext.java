package org.zhire.design.strategy.celve_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过Spring将实现Strategy的实现类都自动注入到strategyMap类中，
 * 当用户传入选择的资源池时，自动根据资源池id去对应的资源池实现中查找资源。
 *
 * @Author: chenqi
 * @Date: 2019.7.25 16:38
 */
@Component
public class SimpleContext {


   @Autowired
   private final Map<String, Strategy> strategyMap = new ConcurrentHashMap<>();

    public SimpleContext(Map<String, Strategy> strategyMap) {
        this.strategyMap.clear();
        for (Map.Entry<String, Strategy> entry : strategyMap.entrySet()) {
            String k = entry.getKey();
            Strategy v = entry.getValue();
            Strategy put = this.strategyMap.put(k, v);
            System.out.println(put);
        }
    }

    public String getResource(String poolId) {
        return strategyMap.get(poolId).getVpcList(poolId);
    }


}
