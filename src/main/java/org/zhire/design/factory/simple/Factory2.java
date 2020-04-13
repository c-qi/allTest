package org.zhire.design.factory.simple;

/**
 * @Author: chenqi
 * @Date: 2019.8.19 18:20
 */
public class Factory2 {


    public static Object getAnimal(Class<? extends Animal> aClass) {
        Object obj = null;
        try {
            obj = Class.forName(aClass.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }


}
