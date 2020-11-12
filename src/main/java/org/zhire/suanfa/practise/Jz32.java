package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * 　　　3
 * 　　/  \
 * 　9 　 20
 * 　　　/  \
 * 　　15   7
 * 返回：
 * [3,9,20,15,7]
 *
 * @Date 2020/11/12 16:57
 * @Author by chenqi
 */
public class Jz32 {
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
     * 二叉树的 从上至下 打印（即按层打印），又称为二叉树的 广度优先搜索（BFS）。
     * BFS 通常借助 队列 的先入先出特性来实现。
     * @param root
     * @return
     */
    public int[] levelOrder2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList() {{
            add(root);
        }};
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node);
            ans.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
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
        System.out.println(Arrays.toString(levelOrder2(node)));
    }

}
