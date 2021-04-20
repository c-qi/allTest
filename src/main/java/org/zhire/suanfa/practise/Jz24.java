package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.Stack;

/**
 * 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * @Date 2020/11/3 16:59
 * @Author by chenqi
 */
public class Jz24 {

    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    // 基于栈
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        // 第一次循环 把结点放入栈
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode newListNode = stack.pop();
        ListNode temp = newListNode;
        // 弹栈 组成新的链表
        while (!stack.empty()) {
            newListNode.next = stack.pop();
            newListNode = newListNode.next;
        }
        // 最后一个结点就是反转前的头结点，要等于空，否则会构成环
        newListNode.next = null;
        return temp;
    }

    // 基于双链表
    public ListNode reverseList2(ListNode head) {
        // 1 2 3 4 5
        // null
        // 1
        // 2 1
        // 3 2 1
        // 4 3 2 1
        // 5 4 3 2 1
        ListNode node = null;
        while (head != null) {
            // 记录下个结点 下次循环用
            ListNode temp = head.next;
            head.next = node;
            node = head;
            head = temp;
        }
        return node;
    }

    @Test
    public void r() {
        ListNode node = new ListNode(4);
        ListNode nodeNext = new ListNode(5);
        ListNode nodeNextNext = new ListNode(1);
        ListNode nodeNextNextNext = new ListNode(9);
        node.next = nodeNext;
        nodeNext.next = nodeNextNext;
        nodeNextNext.next = nodeNextNextNext;
        System.out.println(node);
        System.out.println(reverseList(node));
        // System.out.println(reverseList2(node));
    }

}
