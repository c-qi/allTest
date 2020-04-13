package org.zhire.design.factory.simple;

/**
 * @Author: chenqi
 * @Date: 2019.8.19 18:18
 */
public class Test2 {
    public static void main(String[] args) {
        Animal cat = (Animal) Factory2.getAnimal(Cat.class);
        Animal dog = (Animal) Factory2.getAnimal(Dog.class);
        cat.speak();
        dog.speak();
    }
}
