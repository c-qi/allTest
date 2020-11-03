package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

/**
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 * <p>
 * 注意：此题对比原题有改动
 * <p>
 * 示例 1:
 * <p>
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 * <p>
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 * @Date 2020/11/3 14:20
 * @Author by chenqi
 */
public class Jz18 {
    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode newListNode = head;
        // 如果删除的是头结点 直接返回下个结点
        if (val == newListNode.val) {
            return head.next;
        }
        while (null != newListNode) {
            // 如果被删除数是当前结点的下个结点值 则把当前的结点指向当前结点的下下个结点 然后结束循环
            if (val == newListNode.next.val) {
                newListNode.next = newListNode.next.next;
                return head;
            }
            newListNode = newListNode.next;
        }
        return head;
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
        System.out.println(deleteNode(node, 5));
    }
}
