package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *    3
 * /    \
 * 9    20
 *     /   \
 *    15    7
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/16 15:40
 * @Author by chenqi
 */
public class Jianzhi7 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode node = null;
        for (int i = 0; i < preorder.length; i++) {
            if (node == null) {
                node = new TreeNode(preorder[i]);
            } else {
                int v = node.val;
                if (preorder[i] > v) {
                    if (node.right == null) {
                        node.right = new TreeNode(preorder[i]);
                    } else {

                    }
                }
            }

        }

        return null;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0,
                length - 1, inorder, 0, length - 1, indexMap);
        return root;
    }

    /**
     * @param preorder      前序遍历数组
     * @param preorderStart 前序遍历的开头
     * @param preorderEnd   前序遍历的结尾
     * @param inorder       中序遍历数组
     * @param inorderStart  中序遍历的开头
     * @param inorderEnd    中序遍历的结尾
     * @param indexMap      使用一个Map存储中序遍历的每个元素及其对应的下标，目的是为了快速获得一个元素在中序遍历中的位置
     *                      <p>
     *                      前序遍历 preorder = [3,9,20,15,7]
     *                      中序遍历 inorder = [9,3,15,20,7]
     * @return
     */
    public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd,
                              int[] inorder, int inorderStart, int inorderEnd,
                              Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        // 获取根节点位置-前序遍历的第一个元素
        int rootVal = preorder[preorderStart];
        // 构建二叉树
        TreeNode root = new TreeNode(rootVal);
        // 如果前序遍历的头等于前序遍历的尾 则当前树构建完成
        if (preorderStart == preorderEnd) {
            return root;
        } else {
            // 获取前序遍历根节点在中序遍历中的下标
            int rootIndex = indexMap.get(rootVal);
            // 获取左子树 根节点的下标减去中序遍历的头结点下标
            int leftNodes = rootIndex - inorderStart,
                    // 获取右子树 中序遍历的尾结点下标减去根节点的下标
                    rightNodes = inorderEnd - rootIndex;
            // 递归构建左子树
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes,
                    inorder, inorderStart, rootIndex - 1, indexMap);
            // 递归构建右子树
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd,
                    inorder, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }


    @Test
    public void test() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree2(preorder, inorder));
    }
}
