package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/11/3 18:54
 * @Author by chenqi
 */
public class Jz25 {
    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 两个链表合并成新的链表
        List<Integer> list = new ArrayList<>();
        while (l1 != null) {
            list.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            list.add(l2.val);
            l2 = l2.next;
        }
        System.out.println(list);
        Object[] array = list.toArray();
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        // 建立虚拟头结点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (int i = 0; i < array.length; i++) {

        }

        return null;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0);
        ListNode cur = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    @Test
    public void r() {
        ListNode node = new ListNode(1);
        ListNode nodeNext = new ListNode(3);
        ListNode nodeNextNext = new ListNode(5);
        node.next = nodeNext;
        nodeNext.next = nodeNextNext;
        System.out.println(node);
        ListNode node2 = new ListNode(2);
        ListNode nodeNext2 = new ListNode(4);
        ListNode nodeNextNext2 = new ListNode(6);
        node2.next = nodeNext2;
        nodeNext2.next = nodeNextNext2;
        System.out.println(node2);
        // System.out.println(mergeTwoLists(node, node2));
        System.out.println(mergeTwoLists2(node, node2));

    }
}
