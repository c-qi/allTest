package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的前序遍历
 * <p>
 * 给定一个二叉树，返回它的 前序 遍历。
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 *
 * @Date 2020/10/27 11:40
 * @Author by chenqi
 */
public class Lc144 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        System.out.println(root);
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return list;
    }

    @Test
    public void test() {
        TreeNode treeNode = new TreeNode();
        TreeNode treeNodeRight = new TreeNode();
        TreeNode treeNodeRightLeft = new TreeNode();
        treeNodeRightLeft.val = 3;
        treeNodeRight.val = 2;
        treeNode.val = 1;
        treeNode.left = null;
        //treeNode.left.val = -1;
        treeNode.right = treeNodeRight;
        treeNode.right.left = treeNodeRightLeft;
        System.out.println(treeNode);
        System.out.println(preorderTraversal(treeNode));
    }
}
