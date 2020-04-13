package org.zhire.design.sigle;

/**
 * 饿汉式单例模式
 * JVM在加载这个类时就马上创建此唯一的单例实例，
 * 不管你用不用，先创建了再说，如果一直没有被使用，便浪费了空间，典型的空间换时间，每次调用的时候，就不需要再判断，节省了运行时间。
 *
 * @Author: chenqi
 * @Date: 2019.9.25 10:21
 */
public class Sigle {

    private static Sigle sigle = new Sigle();

    private Sigle() {
    }

    public static Sigle getSigle() {
        return sigle;
    }
}
