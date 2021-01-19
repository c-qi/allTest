package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 4
 *
 * @Date 2021/1/19 14:51
 * @Author by chenqi
 */
public class JZ54 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<Integer> list = new ArrayList();

    public int kthLargest(TreeNode root, int k) {
        if (root == null) return -1;
        getNode(root);
        // List<Integer> list2 = this.list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        // return list2.get(list.size()- 1);
        return list.get(list.size() - k);
    }

    // 中序遍历二叉搜索树就是有序的
    private void getNode(TreeNode root) {
        if (root == null) return;
        getNode(root.left);
        System.out.println(root);
        list.add(root.val);
        getNode(root.right);
    }

    @Test
    public void t() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.left.right = new TreeNode(2);
        System.out.println(kthLargest(treeNode, 1));

    }
}
