package org.zhire.design.factory.simple;

/**
 * 动物接口的具体实现类--狗
 *
 * @Author: chenqi
 * @Date: 2019.8.19 18:13
 */
public class Dog implements Animal {
    public Dog() {
        System.out.println("dog dog dog");
    }

    @Override
    public void speak() {
        System.out.println("wang wang wang");
    }
}
