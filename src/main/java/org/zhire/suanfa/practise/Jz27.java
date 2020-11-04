package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

/**
 * 剑指 Offer 27. 二叉树的镜像（二叉树翻转）
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 例如输入：
 * <p>
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 * <p>
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/11/4 15:52
 * @Author by chenqi
 */
public class Jz27 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 暂存左子树
        TreeNode temp = root.left;
        // 递归右子树 赋值给左子树
        root.left = mirrorTree(root.right);
        // 递归左子树 赋值暂存的左子树
        root.right = mirrorTree(temp);
        return root;
    }

    @Test
    public void r() {
        TreeNode node = new TreeNode(1);
        TreeNode nodeLeft = new TreeNode(2);
        TreeNode nodeRight = new TreeNode(3);
        node.left = nodeLeft;
        node.right = nodeRight;
        System.out.println(node);
        System.out.println(mirrorTree(node));

    }

}
