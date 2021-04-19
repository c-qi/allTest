package org.zhire.suanfa.study;

/**
 * 数组，链表，二叉树结构模板
 *
 * @Date 2021/4/16 16:12
 * @Author by chenqi
 */
public class BaseKuangJia {

    /**
     * 数组遍历框架，典型的线性迭代结构
     *
     * @param arr
     */
    void traverse(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 迭代访问 arr[i]
        }
    }

    /* 基本的单链表节点 */
    class ListNode {
        int val;
        ListNode next;
    }

    /**
     * 链表的迭代访问
     *
     * @param head
     */
    void traverseLb(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            // 迭代访问 p.val
        }
    }

    /**
     * 链表的递归访问
     * 兼具迭代和递归结构
     *
     * @param head
     */
    void traverse(ListNode head) {
        // 递归访问 head.val
        traverse(head.next);
    }

    /* 基本的二叉树节点 */
    class TreeNode {
        int val;
        TreeNode left, right;
    }

    /**
     * 二叉树的遍历 典型的非线性递归遍历结构
     *
     * @param root
     */
    void traverse(TreeNode root) {
        traverse(root.left);
        traverse(root.right);
    }

    /* 基本的 N 叉树节点 */
    class TreeNodeMany {
        int val;
        TreeNode[] children;
    }

    /**
     * 多叉树遍历
     *
     * @param root
     */
    void traverse(TreeNodeMany root) {
        for (TreeNode child : root.children)
            traverse(child);
    }
}
