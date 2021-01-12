package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * 输入两个链表，找出它们的第一个公共节点。
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，
 * 链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2021/1/12 16:08
 * @Author by chenqi
 */
public class Jz52 {

    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode h1 = headA, h2 = headB;
        while (h1 != h2) {
            // 如果h1为空，把h2赋值给h1，避免空指针异常
            h1 = h1 == null ? headB : h1.next;
            System.out.println("h1:" + h1);
            h2 = h2 == null ? headA : h2.next;
            System.out.println("h2:" + h2);
        }
        return h1;
    }

    @Test
    public void t() {
        ListNode l1 = new ListNode(4);
        ListNode l1next = new ListNode(1);
        ListNode l1nextnext = new ListNode(8);
        ListNode l1nextnextnext = new ListNode(4);
        ListNode l1nextnextnextnext = new ListNode(5);
        l1.next = l1next;
        l1next.next = l1nextnext;
        l1nextnext.next = l1nextnextnext;
        l1nextnextnext.next = l1nextnextnextnext;
        System.out.println(l1);

        ListNode l2 = new ListNode(6);
        ListNode l2next = new ListNode(7);
        l2.next = l2next;
        l2next.next = l1nextnext;
        l1nextnext.next = l1nextnextnext;
        l1nextnextnext.next = l1nextnextnextnext;
        System.out.println(l2);

        System.out.println(getIntersectionNode(l1, l2));
    }
}
