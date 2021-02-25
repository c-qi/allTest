package org.zhire.design.strategy.celve_demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

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
    private  Map<String, Strategy> strategyMap ;


    public String getResource(String poolId) {
        System.out.println("map:"+JSON.toJSONString(strategyMap));
        JSONObject object = new JSONObject();
        object.put("name","cq");
        return strategyMap.get(poolId).getVpcList(object);
    }


}
