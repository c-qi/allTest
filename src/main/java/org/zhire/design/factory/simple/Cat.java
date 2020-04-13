package org.zhire.design.factory.simple;

/**
 * 动物接口的具体实现类--猫
 *
 * @Author: chenqi
 * @Date: 2019.8.19 18:12
 */
public class Cat implements Animal {

    public Cat() {
        System.out.println("cat cat cat");
    }

    @Override
    public void speak() {
        System.out.println("miao miao miao");
    }
}

