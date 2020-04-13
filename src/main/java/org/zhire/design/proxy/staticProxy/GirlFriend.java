package org.zhire.design.proxy.staticProxy;

/**
 * 代理对象
 *
 * @Author: chenqi
 * @Date: 2019.9.24 09:49
 */
public class GirlFriend implements GiveGift {
    Man man;

    public GirlFriend(Girl girl) {
        man = new Man(girl);
    }

    @Override
    public void giveFlowers() {
        man.giveFlowers();
    }
}
