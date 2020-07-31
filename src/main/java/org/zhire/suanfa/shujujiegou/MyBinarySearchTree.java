package org.zhire.suanfa.shujujiegou;

import lombok.Data;

/**
 * 二叉查找树
 * 在树中的任意一个节点，
 * 左子树中的每个节点的值，都要小于这个节点的值，
 * 右子树节点的值都大于这个节点的值。
 *
 * @Author: chenqi
 * @Date: 2020.7.31 16:24
 */
@Data
public class MyBinarySearchTree<T> {
    private Node tree;

    @Data
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 插入
     *
     * @param value
     */
    private void insert(int value) {
        if (null == tree) { // 二叉查找树为空，直接插入到根节点
            tree = new Node(value);
            return;
        }
        Node newNode = tree;
        while (newNode != null) {
            if (newNode.data < value) { // 如果插入的值比根节点大
                if (newNode.right == null) { // 如果要插入的位置为空直接插入
                    newNode.right = new Node(value);
                    return;
                }
                newNode = newNode.right; // 如果要插入的位置不为空，则更新newNode为当前遍历到的节点
            } else {
                if (newNode.left == null) {
                    newNode.left = new Node(value);
                    return;
                }
                newNode = newNode.left;
            }
        }
    }

    public static void main(String[] args) {
        MyBinarySearchTree tree = new MyBinarySearchTree();
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        System.out.println(tree);
    }


}
