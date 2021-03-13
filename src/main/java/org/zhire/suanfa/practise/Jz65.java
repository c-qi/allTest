package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 * @Date 2021/3/12 17:39
 * @Author by chenqi
 */
public class Jz65 {
    public int add(int a, int b) {
        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    @Test
    public void t() {
        System.out.println(add(-1, 2));
    }
}
