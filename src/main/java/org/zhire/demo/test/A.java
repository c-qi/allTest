package org.zhire.demo.test;

//@Data
public class A {
    public long id = 1L;

    public A() {
        r();
        w();
    }

    public void r() {
        System.out.println("rrrr");
    }

    private void w() {
        System.out.println("wwww");
    }
}
