package org.zhire.design.observer;

/**
 * @Author: chenqi
 * @Date: 2019.9.20 16:32
 */
public class Lisi implements ILisi {
    @Override
    public void update(String message) {
        System.out.println(message);
        System.out.println("Know Hanfeizi is doing something");
    }
}
