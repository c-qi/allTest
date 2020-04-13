package org.zhire.design.bulider;

/**
 * 具体建造者
 *
 * @Author: chenqi
 * @Date: 2019.9.19 14:12
 */
public class AnimalFish extends AnimalBulider {

    @Override
    public void bulidName() {
        animal.setName("fish");
    }

    @Override
    public void bulidLeg() {
        animal.setLeg("No");

    }

    @Override
    public void bulidWings() {
        animal.setWings("No");
    }
}
