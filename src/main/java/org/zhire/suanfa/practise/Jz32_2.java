package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * 　  3
 * 　/ 　 \
 * 9   　 20
 * 　　/ 　 　 \
 * 　 15  　  7
 * 返回其层次遍历结果：
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * @Date 2020/11/13 14:32
 * @Author by chenqi
 */
public class Jz32_2 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        if(root != null) {
            queue.add(root);
        }
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    @Test
    public void t() {
        TreeNode node = new TreeNode(3);
        TreeNode nodeLeft = new TreeNode(9);
        TreeNode nodeRight = new TreeNode(20);
        TreeNode nodeRightLeft = new TreeNode(15);
        TreeNode nodeRightRight = new TreeNode(7);
        node.left = nodeLeft;
        nodeLeft.left = null;
        nodeLeft.right = null;
        node.right = nodeRight;
        nodeRight.left = nodeRightLeft;
        nodeRight.right = nodeRightRight;
        System.out.println(levelOrder(node));
    }
}
