package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/23 11:19
 * @Author by chenqi
 */
public class Jianzhi9 {
    Deque<Integer> deque1;
    Deque<Integer> deque2;

    class CQueue {

        public CQueue() {
            deque1 = new LinkedList();
            deque2 = new LinkedList();
        }

        public void appendTail(int value) {
            deque1.push(value);
        }

        public int deleteHead() {
            if (deque2.isEmpty()) {
                while (!deque1.isEmpty()) {
                    deque2.push(deque1.pop());
                }
            }
            if (deque2.isEmpty()) {
                return -1;
            }
            return deque2.pop();
        }
    }

    @Test
    public void twst() {
        CQueue c = new CQueue();
        c.appendTail(1);
        c.appendTail(2);
        c.appendTail(3);
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());


    }
}
