package org.zhire.design.sigle;

/**
 * 一 单例模式
 * 1.1 定义
 * 保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 *
 * 1.2 为什么要用单例模式
 * 在我们的系统中，有一些对象其实我们只需要一个，比如说：线程池、缓存、对话框、注册表、日志对象、充当打印机、显卡等设备驱动程序的对象。
 * 事实上，这一类对象只能有一个实例，如果制造出多个实例就可能会导致一些问题的产生，比如：程序的行为异常、资源使用过量、或者不一致性的结果。
 *
 * 使用单例模式可以带来下面几个好处:
 *
 * 对于频繁使用的对象，可以省略创建对象所花费的时间，这对于那些重量级对象而言，是非常可观的一笔系统开销；
 * 由于 new 操作的次数减少，因而对系统内存的使用频率也会降低，这将减轻 GC 压力，缩短 GC 停顿时间。
 *
 *
 * @Author: chenqi
 * @Date: 2019.9.25 11:10
 */
public class Test {
    public static void main(String[] args) {

//        Sigle sigle = Sigle.getSigle();
//        Sigle sigle1 = Sigle.getSigle();
//        boolean b = sigle.equals(sigle1);
//        System.out.println(b);

//        Sigle2 sigle = Sigle2.getSigle();
//        Sigle2 sigle1 = Sigle2.getSigle();
//        boolean b = sigle.equals(sigle1);
//        System.out.println(b);

        Sigle3 sigle = Sigle3.getSigle();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Sigle3 sigle1 = Sigle3.getSigle();
                boolean b = sigle.equals(sigle1);
                System.out.println(b);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


    }
}
