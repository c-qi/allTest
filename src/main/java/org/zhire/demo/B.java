package org.zhire.demo;

//@Data
public class B extends A {
    public long id = 2L;

    public void r() {
        System.out.println("r");
    }

    private void w() {
        System.out.println("w");
    }

    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.id);
        a.r();

    }
}

