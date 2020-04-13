package org.zhire.design.factory.simple;

/**
 * 工厂类
 *
 * @Author: chenqi
 * @Date: 2019.8.19 18:15
 */
public class Factory {

    public static Animal getAnimal(String ani) {
        if (ani == null) {
            return null;
        } else if ("cat".equals(ani)) {
            return new Cat();
        } else if ("dog".equals(ani)) {
            return new Dog();
        }
        return null;
    }

}
