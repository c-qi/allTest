package org.zhire.design.factory.methodFactory;

import org.zhire.design.factory.simple.Animal;
import org.zhire.design.factory.simple.Dog;

/**
 * 动物抽象工厂的具体实现--创建狗的工厂
 *
 * @Author: chenqi
 * @Date: 2019.8.27 15:34
 */
public class DogFactory implements AnimalFactory {

    @Override
    public Animal getAnimal() {
        return new Dog();
    }
}
