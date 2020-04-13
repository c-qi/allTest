package org.zhire.design.factory;

import org.zhire.design.factory.simple.Animal;
import org.zhire.design.factory.simple.Cat;

/**
 * 动物抽象工厂的具体实现--创建猫的工厂
 *
 * @Author: chenqi
 * @Date: 2019.8.27 15:34
 */
public class CatFactory implements AnimalFactory {

    @Override
    public Animal getAnimal() {
        return new Cat();
    }
}
