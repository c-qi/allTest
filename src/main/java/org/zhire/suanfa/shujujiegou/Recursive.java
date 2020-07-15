package org.zhire.suanfa.shujujiegou;

/**
 * 递归
 *
 * @Author: chenqi
 * @Date: 2020.7.14 17:34
 */
public class Recursive {

    public static void main(String[] args) {
        // System.out.println("maingetFn:" + getFn(4));
        // System.out.println("maingetNnnn:" + getNnnn(3));
        String[] s = new String[]{"1", "2", "3"};
        perm(s, 0, s.length - 1);
    }


    // 求n级台阶有多少种走法 每次可以走1步或者2步
    // 1 2 3 4
    // 12 34
    // 1 23 4
    // 12 3 4
    // 1 2 34
    // 即求值f(n)=f(n-1)+f(n-2)
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
    // n! = n*(n-1)!
    // 0！= 1 0的阶乘
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

    // 实现一组数据集合的全排列
    private static void perm(String list[], int k, int m) {
        if (k == m) { // 没有到最后一个   所以还需要进行排列
            for (int i = 0; i < list.length; i++) {
                System.out.print(list[i] + " ");
            }
            System.out.println();
        } else { // 进行排列
            for (int i = k; i <= m; i++) {
                String c = list[i]; // 进行交换  从而完成全排列
                list[i] = list[k];
                list[k] = c;
                perm(list, k + 1, m);
                c = list[i]; // 需要交换回来 不然就会乱了顺序
                list[i] = list[k];
                list[k] = c;
            }
        }
    }
}
