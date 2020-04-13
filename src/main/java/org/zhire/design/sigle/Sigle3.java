package org.zhire.design.sigle;

/**
 * 懒汉单例模式(双重检查加锁版本)
 * 利用双重检查加锁（double-checked locking），首先检查是否实例已经创建，
 * 如果尚未创建，“才”进行同步。这样以来，只有一次同步，这正是我们想要的效果。
 *
 * @Author: chenqi
 * @Date: 2019.9.25 10:21
 */
public class Sigle3 {

    //volatile保证，当sigle变量被初始化成Sigle3实例时，多个线程可以正确处理sigle变量 禁止指令重排
    private volatile static Sigle3 sigle;

    private Sigle3() {
    }

    public static Sigle3 getSigle() {
        if (sigle == null) {
            synchronized (Sigle3.class) {
                if (sigle == null) {
                    sigle = new Sigle3();
                }
            }
        }
        return sigle;
    }
}
