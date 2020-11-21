package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * 链表排序
 *
 * @Date 2020/11/21 17:28
 * @Author by chenqi
 */
public class Lc148 {
    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Object[] objects = list.toArray();
        Arrays.sort(objects, Collections.reverseOrder());
        Stack<ListNode> stack = new Stack<>();
        for (int i = 0; i < objects.length; i++) {
            stack.push(new ListNode((Integer) objects[i]));
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

    public ListNode sortList2(ListNode head) {
        if (head == null) return null;
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.sort(Comparator.comparingInt(n -> n.val));
        System.out.println(list);
        for (int i = 1; i < list.size(); i++) {
            list.get(i - 1).next = list.get(i);
        }
        list.get(list.size() - 1).next = null;
        return list.get(0);
    }

    @Test
    public void t() {
        ListNode node = new ListNode(3);
        ListNode nodeNext = new ListNode(2);
        ListNode nodeNextNext = new ListNode(0);
        node.next = nodeNext;
        nodeNext.next = nodeNextNext;
        System.out.println(sortList(node));
        System.out.println(sortList2(node));
    }
}
