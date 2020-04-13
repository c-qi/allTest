package org.zhire.design.factory.abstractFactory;

/**
 * 狗工厂
 *
 * @Author: chenqi
 * @Date: 2019.8.27 17:10
 */
public class DogFactory implements Factory {
    @Override
    public Animal animalEat() {
        return new Dog();
    }

    @Override
    public Food animalFood() {
        return new Shit();
    }
}
