package org.zhire.demo.test;

import lombok.Data;

@Data
public class A {
    public long id = 1L;

    private String name="1234213";

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

    public static void main(String[] args) {
        A a =new A();
        a.name = "1";
    }
}
