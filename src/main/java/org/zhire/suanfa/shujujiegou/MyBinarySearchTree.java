package org.zhire.suanfa.shujujiegou;

import lombok.Data;
import lombok.val;

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

    /**
     * 查询
     *
     * @param value
     * @return
     */
    private Node find(int value) {
        Node newNode = tree;
        while (newNode != null) {
            if (value > newNode.data) {
                newNode = newNode.right;
            } else if (value < newNode.data) {
                newNode = newNode.left;
            } else if (value == newNode.data) {
                return newNode;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 删除
     * <p>
     * 1)如果要删除的节点没有子节点，我们只需要直接将父节点中，指向要删除节点的指针置为 null。
     * 2)如果要删除的节点只有一个子节点（只有左子节点或者右子节点），我们只需要更新父节点中，指向要删除节点的指针，让它指向要删除节点的子节点就可以了。
     * 3)如果要删除的节点有两个子节点，需要找到这个节点的右子树中的最小节点，把它替换到要删除的节点上。
     * 然后再删除掉这个最小节点，因为最小节点肯定没有左子节点（如果有左子结点，那就不是最小节点了），
     * 所以，可以应用上面两条规则来删除这个最小节点。
     *
     * @param data
     */
    private void delete(int data) {
        Node p = tree; // p指向要删除的节点，初始化指向根节点
        Node pp = null; // pp记录的是p的父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) { // 没有找到
            return;
        }
        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            Node minP = p.right;
            Node minPP = p; // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // 将minP的数据替换到p中
            p = minP; // 下面就变成了删除minP了
            pp = minPP;
        } // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }
        if (pp == null) {
            tree = child;
        } // 删除的是根节点
        else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }

    }

    public static void main(String[] args) {
        MyBinarySearchTree tree = new MyBinarySearchTree();
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        tree.insert(5);
        tree.insert(0);
        tree.insert(-1);
        System.out.println(tree);
        System.out.println(tree.find(2));
        tree.delete(2);
        System.out.println(tree.find(2));
    }


}
