package org.zhire.design.factory.abstractFactory;

/**
 * 鱼
 *
 * @Author: chenqi
 * @Date: 2019.8.27 17:08
 */
public class Fish implements Food {
    @Override
    public void food() {
        System.out.println("fish");
    }
}
