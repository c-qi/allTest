package org.zhire.design.observer.observer1;


/**
 * @Author: chenqi
 * @Date: 2019.9.20 16:35
 */
public class Spy extends Thread {
    private Hanfeizi hanfeizi;
    private Lisi lisi;
    private String str;

    public Spy(Hanfeizi hanfeizi, Lisi lisi, String str) {
        this.hanfeizi = hanfeizi;
        this.lisi = lisi;
        this.str = str;
    }

    @Override
    public void run() {
        while (true) {
            if (str.equals("breakfast")) {
                if (hanfeizi.isHaveBreakfast()) {
                    lisi.update("discover han is having breakfast...");
                    hanfeizi.setHaveBreakfast(false);
                }
            } else {
                if (hanfeizi.isHaveFun()) {
                    lisi.update("discover han is having fun...");
                    hanfeizi.setHaveFun(false);
                }
            }
        }
    }
}
