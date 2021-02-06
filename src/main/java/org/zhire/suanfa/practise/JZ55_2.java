package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * /  \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 *
 * @Date 2021/2/6 14:42
 * @Author by chenqi
 */
public class JZ55_2 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Math.abs(self.depth(root.left) - self.depth(root.right)) <= 1 ：判断 当前子树 是否是平衡树；
     * isBalanced(root.left) ： 先序遍历递归，判断 当前子树的左子树 是否是平衡树；
     * isBalanced(root.right) ： 先序遍历递归，判断 当前子树的右子树 是否是平衡树；
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        System.out.println(root);
        return Math.abs(getHigh(root.left) - getHigh(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);

    }

    /**
     * depth(root) 函数： 计算树 root 的深度
     * <p>
     * 终止条件： 当 root 为空，即越过叶子节点，则返回高度 00 ；
     * 返回值： 返回左 / 右子树的深度的最大值 +1+1 。
     *
     * @param root
     * @return
     */
    private int getHigh(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHigh(root.left), getHigh(root.right)) + 1;
    }


    @Test
    public void t() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        System.out.println(isBalanced(treeNode));
    }


}
