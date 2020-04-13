package org.zhire.design.bulider;

/**
 * 指挥者
 *
 * @Author: chenqi
 * @Date: 2019.9.19 14:21
 */
public class AnimalDirector {
    private AnimalBulider animalBulider;


    public void setAnimalBulider(AnimalBulider animalBulider) {
        this.animalBulider = animalBulider;
    }

    public Animal construct() {
        animalBulider.bulidName();
        animalBulider.bulidLeg();
        animalBulider.bulidWings();
        return animalBulider.getAnimal();
    }


}
