package org.zhire.design.bulider;

/**
 * 动物建造者
 *
 * @Author: chenqi
 * @Date: 2019.9.19 13:58
 */
public abstract class AnimalBulider {

    Animal animal = new Animal();

    public abstract void bulidName();

    public abstract void bulidLeg();

    public abstract void bulidWings();

    public Animal getAnimal() {
        return animal;
    }

}
