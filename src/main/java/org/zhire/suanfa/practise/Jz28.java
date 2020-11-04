package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 *
 * @Date 2020/11/4 16:30
 * @Author by chenqi
 */
public class Jz28 {
    //@Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getTree(root.left, root.right);
    }

    boolean getTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return getTree(left.left, right.right) && getTree(left.right, right.left);
    }

    @Test
    public void r() {
        TreeNode node = new TreeNode(1);
        TreeNode nodeLeft = new TreeNode(2);
        TreeNode nodeRight = new TreeNode(2);
        node.left = nodeLeft;
        node.right = nodeRight;
        node.left.left = null;
        node.left.right = new TreeNode(3);
        node.right.left = null;
        node.right.right = new TreeNode(3);
        System.out.println(node);
        System.out.println(isSymmetric(node));

    }
}
