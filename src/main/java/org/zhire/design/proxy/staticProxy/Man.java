package org.zhire.design.proxy.staticProxy;

/**
 * 真实对象
 *
 * @Author: chenqi
 * @Date: 2019.9.24 09:37
 */
public class Man implements GiveGift {
    Girl girl;

    public Man(Girl girl) {
        this.girl = girl;
    }

    @Override
    public void giveFlowers() {
        System.out.println(girl.name + ": give flowers!");
    }
}
