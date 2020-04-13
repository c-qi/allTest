package org.zhire.design.strategy.celve_demo;

import org.springframework.stereotype.Component;

/**
 * @Author: chenqi
 * @Date: 2019.7.25 16:38
 */

@Component("B")
public class ResourceB implements Strategy {

    @Override
    public String getVpcList(String id) {
        System.out.println("B strategy" + "=====" + id);
        return id;
    }

}