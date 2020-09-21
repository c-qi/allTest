package org.zhire.design.adapter;

public class Adaptor extends Adaptee implements Target{
    @Override
    public void f1() {
        super.fa();
        System.out.println("Adaptor f1()");
    }

    @Override
    public void f2() {
        // 重新实现f2()...
        System.out.println("Adaptor f2()");
    }

    // 这⾥fc()不需要实现，直接继承⾃Adaptee，这是跟对象适配器最⼤的不同点

    public static void main(String[] args) {
        Adaptor adaptor = new Adaptor();
        adaptor.f1();
        adaptor.f2();
        adaptor.fc();
    }
}
