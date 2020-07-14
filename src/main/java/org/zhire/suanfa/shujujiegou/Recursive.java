package org.zhire.suanfa.shujujiegou;

/**
 * 递归
 *
 * @Author: chenqi
 * @Date: 2020.7.14 17:34
 */
public class Recursive {

    public static void main(String[] args) {
        System.out.println("maingetFn:" + getFn(4));
        System.out.println("maingetNnnn:" + getNnnn(3));
    }
    // 1 2 3 4
    // 12 34
    // 1 23 4
    // 12 3 4
    // 1 2 34


    // 求值f(n)=f(n-1)+f(n-2)
    // f(1) = 1
    // f(2) = 2
    private static int getFn(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        System.out.println("n: " + n);
        int fn = getFn(n - 1);
        System.out.println("fn:" + fn);


        System.out.println("nnnnnnnnnnnnn: " + n);
        int fn2 = getFn(n - 2);
        System.out.println("fn2:" + fn2);

        System.out.println("fn1111111:" + fn);
        System.out.println("fn2222222:" + fn2);
        return fn + fn2;
    }

    // 求阶乘n!
    // 0的阶乘
    // 0！= 1
    // 1！= 1
    // 2! = 2 * f(1)! = 2 * 1 = 2
    // 3! = 3* f(2)! = 3 * 2 = 6
    private static int getNnnn(int num) {
        if (num == 1) {
            return 1;
        }
        int result = num * getNnnn(num - 1);
        return result;
    }


}
