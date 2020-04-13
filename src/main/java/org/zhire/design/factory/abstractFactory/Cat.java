package org.zhire.design.factory.abstractFactory;

/**
 * 猫
 *
 * @Author: chenqi
 * @Date: 2019.8.27 17:05
 */
public class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("cat eat fish");
    }
}
