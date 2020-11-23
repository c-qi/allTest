package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * <p>
 * <p>
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 　　 　 5
 * 　 　 / 　\
 * 　　4      8
 * 　/     /  　\
 * 11  13 　     4
 * /  \         / \
 * 7   2       5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 *
 * @Date 2020/11/23 14:29
 * @Author by chenqi
 */
public class Jz34 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        TreeNode node = root;
        List<String> list = getNode(node, "");
        for (String s : list) {
            String[] s1 = s.trim().split(" ");
            Integer all = 0;
            for (String s2 : s1) all += Integer.parseInt(s2);
            if (!all.equals(sum)) continue;
            List<Integer> integerList = new ArrayList<>();
            for (String s2 : s1) integerList.add(Integer.parseInt(s2));
            result.add(integerList);
        }
        return result;
    }

    List list = new ArrayList();

    private List<String> getNode(TreeNode node, String value) {
        if (node == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(value).append(" ").append(node.val);
        if (node.left == null && node.right == null) {
            list.add(sb.toString());
            return list;
        }
        getNode(node.left, sb.toString());
        getNode(node.right, sb.toString());
        return list;
    }

    @Test
    public void t() {
        TreeNode node = new TreeNode(1);
        TreeNode nodeLeft = new TreeNode(7);
        TreeNode nodeRight = new TreeNode(3);
        TreeNode nodeRightLeft = new TreeNode(4);
        TreeNode nodeRightRight = new TreeNode(6);
        node.left = nodeLeft;
        nodeLeft.left = null;
        nodeLeft.right = null;
        node.right = nodeRight;
        nodeRight.left = nodeRightLeft;
        nodeRight.right = nodeRightRight;
        System.out.println(pathSum(node, 8));

    }

}
