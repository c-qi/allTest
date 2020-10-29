package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 输入: [4,9,0,5,1]
 * 　　　4
 * 　　/ 　\
 * 　9 　  0
 * / 　\
 * 5　　1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/29 11:18
 * @Author by chenqi
 */
public class Lc129 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 计算int结果
    private int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    // 不计算 使用字符串拼接
    private int sumNumbers2(TreeNode root) {
        return dfs2(root, "");
    }

    private int dfs2(TreeNode root, String value) {
        if (root == null) {
            return 0;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append(value).append(root.val);
        if (root.left == null && root.right == null) {
            return Integer.parseInt(sb.toString());
        }
        return dfs2(root.left, sb.toString()) + dfs2(root.right, sb.toString());
    }

    /**
     * 二叉树的每条从根节点到叶子节点的路径都代表一个数字。其实，每个节点都对应一个数字，
     * 等于其父节点对应的数字乘以 10 再加上该节点的值（这里假设根节点的父节点对应的数字是0）。
     * 只要计算出每个叶子节点对应的数字，然后计算所有叶子节点对应的数字之和，即可得到结果。
     *
     * @param root
     * @param prevSum
     * @return
     */
    private int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = root.val + prevSum * 10;
        System.out.println(root.val + " " + sum);
        if (root.left == null && root.right == null) {
            return sum;
        }
        int l = dfs(root.left, sum);
        System.out.println("left:" + l);
        System.out.println("right：" + sum);
        int r = dfs(root.right, sum);
        return l + r;
    }


    @Test
    public void t() {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(0);
        TreeNode leftLeft = new TreeNode(5);
        TreeNode leftRight = new TreeNode(1);
        root.left = left;
        root.right = right;
        root.left.left = leftLeft;
        root.left.right = leftRight;
        //System.out.println(sumNumbers(root));
        System.out.println(sumNumbers2(root));
    }
}
