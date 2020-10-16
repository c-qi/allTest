package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * @Date 2020/10/16 14:28
 * @Author by chenqi
 */
public class Jianzhi6 {

    @Data
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 2};
        ListNode node = new ListNode(nums[0]);
        node.next = new ListNode(nums[1]);
        node.next.next = new ListNode(nums[2]);
        System.out.println(node);
        System.out.println(Arrays.toString(reversePrint(node)));
        System.out.println(Arrays.toString(reversePrint2(node)));
    }

    /**
     * 基于List的实现
     *
     * @param head
     * @return
     */
    private int[] reversePrint(ListNode head) {
        int[] result = {};
        List<Integer> arrayList = new ArrayList<>();
        ListNode flag = head;
        if (null == head) {
            return result;
        }
        while (null != flag) {
            arrayList.add(flag.val);
            flag = flag.next;
        }
        result = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = arrayList.get(arrayList.size() - i - 1);
        }
        return result;
    }

    /**
     * 基于Stack的实现
     *
     * @param head
     * @return
     */
    private int[] reversePrint2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        int[] result = {};
        if (null == head) {
            return result;
        }
        while (head != null) {
            stack.add(head.val);
            head = head.next;
        }
        result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pop();
        }
        return result;
    }

}
