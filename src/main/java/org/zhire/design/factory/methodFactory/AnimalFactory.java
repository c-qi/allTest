package org.zhire.design.factory.methodFactory;

import org.zhire.design.factory.simple.Animal;

/**
 * 动物的抽象工厂
 *
 * @Author: chenqi
 * @Date: 2019.8.27 15:32
 */
public interface AnimalFactory {
    Animal getAnimal();
}
