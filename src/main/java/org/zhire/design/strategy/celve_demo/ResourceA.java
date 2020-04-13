package org.zhire.design.strategy.celve_demo;

import org.springframework.stereotype.Component;

/**
 * @Author: chenqi
 * @Date: 2019.7.25 16:37
 */

@Component("A")
public class ResourceA implements Strategy {

    @Override
    public String getVpcList(String id) {
        System.out.println("A,getVpcList ==========="+id);
        return id;
    }


}
