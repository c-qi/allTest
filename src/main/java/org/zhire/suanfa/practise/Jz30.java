package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * @Date 2020/11/4 21:33
 * @Author by chenqi
 */
public class Jz30 {
    class MinStack {

        LinkedList<Integer> list;

        public MinStack() {
            list = new LinkedList<>();
        }

        public void push(int x) {
            list.addFirst(x);
        }

        public void pop() {
            list.remove(list.get(0));
        }

        public int top() {
            return list.get(0);

        }

        public int min() {
            Object[] objects = list.toArray();
            Arrays.sort(objects);
            return (int) objects[0];
        }
    }

    @Test
    public void t() {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.min());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.min());

    }

}
