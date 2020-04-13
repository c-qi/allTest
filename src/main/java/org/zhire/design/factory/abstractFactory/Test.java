package org.zhire.design.factory.abstractFactory;

/**
 * 抽象工厂模式
 *
 * 1 介绍
 * 在工厂方法模式中，其实我们有一个潜在意识的意识。那就是我们生产的都是同一类产品。
 * 抽象工厂模式是工厂方法的仅一步深化，在这个模式中的工厂类不单单可以创建一种产品，而是可以创建一组产品。
 * 2 适用场景
 * 和工厂方法一样客户端不需要知道它所创建的对象的类。
 * 需要一组对象共同完成某种功能时，并且可能存在多组对象完成不同功能的情况。（同属于同一个产品族的产品）
 * 系统结构稳定，不会频繁的增加对象。（因为一旦增加就需要修改原有代码，不符合开闭原则）
 * 3 抽象工厂方法模式角色分配：
 * 抽象工厂（AbstractFactory）角色 ：是工厂方法模式的核心，与应用程序无关。
 *                                  任何在模式中创建的对象的工厂类必须实现这个接口。
 * 具体工厂类（ConreteFactory）角色 ：这是实现抽象工厂接口的具体工厂类，
 *                                  包含与应用程序密切相关的逻辑，并且受到应用程序调用以创建某一种产品对象。
 * 抽象产品（Abstract Product）角色 ：工厂方法模式所创建的对象的超类型，
 *                                  也就是产品对象的共同父类或共同拥有的接口。
 * 具体产品（Concrete Product）角色 ：抽象工厂模式所创建的任何产品对象都是某一个具体产品类的实例。
 *                                  在抽象工厂中创建的产品属于同一产品族，这不同于工厂模式中的工厂只创建单一产品。
 *
 * @Author: chenqi
 * @Date: 2019.8.27 17:11
 */
public class Test {
    public static void main(String[] args) {
        Factory factory = new CatFactory();
        Animal animal = factory.animalEat();
        Food food = factory.animalFood();
        animal.eat();
        food.food();

    }
}
