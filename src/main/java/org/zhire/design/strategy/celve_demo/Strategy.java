package org.zhire.design.strategy.celve_demo;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: chenqi
 * @Date: 2019.7.25 16:37
 */
public interface Strategy {
    String getVpcList(JSONObject jsonObject);
}
