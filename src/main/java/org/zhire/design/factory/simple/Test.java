package org.zhire.design.factory.simple;


/**
 * 工厂模式
 * 1.定义：在基类中定义创建对象的一个接口，让子类决定实例化哪个类。工厂方法让一个类的实例化延迟到子类中进行
 * 2.分类:
 * 1）简单工厂（Simple Factory）模式，又称静态工厂方法模式（Static Factory Method Pattern）。
 * 2）工厂方法（Factory Method）模式，又称多态性工厂（Polymorphic Factory）模式或虚拟构造子（Virtual Constructor）模式；
 * 3）抽象工厂（Abstract Factory）模式，又称工具箱（Kit 或Toolkit）模式。
 * 3.为什么用工厂模式：
 * (1) 解耦 ：把对象的创建和使用的过程分开
 * (2)降低代码重复: 如果创建某个对象的过程都很复杂，需要一定的代码量，而且很多地方都要用到，那么就会有很多的重复代码。
 * (3) 降低维护成本 ：由于创建过程都由工厂统一管理，所以发生业务逻辑变化，不需要找到所有需要创建对象B的地方去逐个修正，只需要在工厂里修改即可，降低维护成本。
 * <p>
 * 1.简单工厂模式
 * 简单工厂模式在实际中的应用相对于其他2个工厂模式用的还是相对少得多，因为它只适应很多简单的情况。
 * 最重要的是它违背了我们在概述中说的 开放-封闭原则 （虽然可以通过反射的机制来避免，后面我们会介绍到）。
 * 因为每次你要新添加一个功能，都需要在生switch-case 语句（或者if-else 语句）中去修改代码，添加分支条件。
 * 2.适用场景
 * （1）需要创建的对象较少。
 * （2）客户端不关心对象的创建过程。
 * 3.简单工厂模式角色分配：
 * 工厂(Factory)角色 :简单工厂模式的核心，它负责实现创建所有实例的内部逻辑。工厂类可以被外界直接调用，创建所需的产品对象。
 * 抽象产品(Product)角色 :简单工厂模式所创建的所有对象的父类，它负责描述所有实例所共有的公共接口。
 * 具体产品(Concrete Product)角色:简单工厂模式的创建目标，所有创建的对象都是充当这个角色的某个具体类的实例。
 *
 * @Author: chenqi
 * @Date: 2019.8.19 18:18
 */
public class Test {
    public static void main(String[] args) {
        Animal cat = Factory.getAnimal("cat");
        Animal dog = Factory.getAnimal("dog");
        cat.speak();
        dog.speak();
    }
}
