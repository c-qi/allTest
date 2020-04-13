package org.zhire.design.sigle;

/**
 * 懒汉单例模式(线程安全的实现)
 * 单例实例在第一次被使用时构建，而不是在JVM在加载这个类时就马上创建此唯一的单例实例。
 *
 * @Author: chenqi
 * @Date: 2019.9.25 10:21
 */
public class Sigle2 {

    private static Sigle2 sigle;

    private Sigle2() {
    }

    public synchronized static Sigle2 getSigle() {
        if (sigle == null) {
            sigle = new Sigle2();
        }
        return sigle;
    }
}
