package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 * @Date 2020/11/3 16:11
 * @Author by chenqi
 */
public class Jz22 {

    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode newNode = head;
        int index = 0;
        // 第一次遍历 找到倒数第k的结点是正数第几个结点
        while (newNode != null) {
            index++;
            newNode = newNode.next;
        }
        int flag = index - k;
        index = 0;
        ListNode newNode2 = head;
        // 第二次遍历 找到正数第几个结点后 返回后面的元素
        while (newNode2 != null) {
            if (index == flag) {
                return newNode2;
            }
            index++;
            newNode2 = newNode2.next;
        }
        return null;
    }

    @Test
    public void t() {
        ListNode node = new ListNode(4);
        ListNode nodeNext = new ListNode(5);
        ListNode nodeNextNext = new ListNode(1);
        ListNode nodeNextNextNext = new ListNode(9);
        node.next = nodeNext;
        nodeNext.next = nodeNextNext;
        nodeNextNext.next = nodeNextNextNext;
        System.out.println(node);
        System.out.println(getKthFromEnd(node, 5));
    }
}
