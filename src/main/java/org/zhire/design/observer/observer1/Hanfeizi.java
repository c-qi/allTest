package org.zhire.design.observer.observer1;

/**
 * @Author: chenqi
 * @Date: 2019.9.20 16:23
 */
public class Hanfeizi implements IHanfeizi {

    private boolean isHaveBreakfast = false;
    private boolean isHaveFun = false;

    public boolean isHaveBreakfast() {
        return isHaveBreakfast;
    }

    public void setHaveBreakfast(boolean haveBreakfast) {
        isHaveBreakfast = haveBreakfast;
    }

    public boolean isHaveFun() {
        return isHaveFun;
    }

    public void setHaveFun(boolean haveFun) {
        isHaveFun = haveFun;
    }

    @Override
    public void haveBreakfast() {
        System.out.println("eat breakfast");
        isHaveBreakfast = true;
    }

    @Override
    public void haveFun() {
        System.out.println("have fun");
        isHaveFun = true;
    }
}
