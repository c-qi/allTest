package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * @Date 2020/10/23 17:44
 * @Author by chenqi
 */
public class Lc234 {
    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        ListNode flag = head.next;
        while (flag != null) {
            list.add(flag.val);
            flag = flag.next;
        }
        System.out.println(list);
        int size = list.size();
        for (int i = 0, j = size - 1; i < size / 2 && j < size; i++, j--) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);
        System.out.println(node);
        System.out.println(isPalindrome(node));

    }

}
