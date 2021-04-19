package org.zhire.suanfa.study.tree;

/**
 * @Date 2021/4/19 17:14
 * @Author by chenqi
 */
public class Lc116TianChongZhiZhen {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;

        connectTwo(root.left, root.right);

        return root;

    }

    private void connectTwo(Node left, Node right) {

        if (left == null || right == null) {
            return;
        }
        /**** 前序遍历位置 ****/
        // 将传入的两个节点连接
        left.next = right;

        // 连接相同父节点的两个子节点
        connectTwo(left.left, left.right);
        connectTwo(right.left, right.right);

        // 连接跨越父节点的两个子节点
        connectTwo(left.right, right.left);

    }
}
