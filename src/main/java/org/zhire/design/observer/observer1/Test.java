package org.zhire.design.observer.observer1;


/**
 * @Author: chenqi
 * @Date: 2019.9.20 16:51
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Lisi lisi = new Lisi();
        Hanfeizi hanfeizi = new Hanfeizi();

        Spy spy = new Spy(hanfeizi, lisi, "breakfast");
        spy.start();
        hanfeizi.haveBreakfast();
    }
}
