package org.zhire.demo.finall;

/**
 * 类被final关键字修饰，那么这个类将不能被继承。
 * 方法被final关键字修饰，该方法将无法被继承的子类进行重写，也就是不允许子类隐藏继承的final方法。
 * final修饰的成员变量，该成员变量就成了常量，且后期的运行过程中不能进行更改，因此，在final修饰变量时，一定要对该常量进行赋值。
 *
 * @Author: chenqi
 * @Date: 2020.7.28 17:28
 */
public class FinallTest {
    static final String s = "aaa";

    public static void main(String[] args) {
        System.out.println(s);
    }


}
