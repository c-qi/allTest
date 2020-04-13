package org.zhire.design.factory.abstractFactory;

/**
 * 猫工厂
 *
 * @Author: chenqi
 * @Date: 2019.8.27 17:10
 */
public class CatFactory implements Factory {
    @Override
    public Animal animalEat() {
        return new Cat();
    }

    @Override
    public Food animalFood() {
        return new Fish();
    }
}
