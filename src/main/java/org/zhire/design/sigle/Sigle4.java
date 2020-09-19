package org.zhire.design.sigle;

/**
 * 基于静态内部类的单例模式，唯⼀性和创建过程的安全性，由JVM来保证
 */
public class Sigle4 {

    private static class Sigle4After {
        static Sigle4 sigle4 = new Sigle4();
    }

    public static Sigle4 get() {
        return Sigle4After.sigle4;
    }

    public static void main(String[] args) {
        Sigle4 s1 = Sigle4.get();
        Sigle4 s2 = Sigle4.get();
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
    }
}
