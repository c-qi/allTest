package org.zhire.suanfa.shujujiegou;

import lombok.Data;

/**
 * 二叉树的前序，中序，后序遍历
 * 　　  a
 * 　　/   \
 * 　 b    c
 * 　/  \
 * d  　 e
 *
 * @Author: chenqi
 * @Date: 2020.7.31 11:05
 */
@Data
public class MyTree<T> {
    T value;
    MyTree<T> left;
    MyTree<T> right;

    /**
     * 前序遍历
     * a-b-d-e-c
     *
     * @param myTree
     */
    private static void before(MyTree myTree) {
        if (myTree == null) {
            return;
        }
        System.out.println(myTree);
        before(myTree.left);
        before(myTree.right);
    }

    /**
     * 中序遍历
     * d-b-e-a-c
     *
     * @param myTree
     */
    private static void middle(MyTree myTree) {
        if (myTree == null) {
            return;
        }
        middle(myTree.left);
        System.out.println(myTree);
        middle(myTree.right);
    }

    /**
     * 后序遍历
     * d-e-b-c-a
     *
     * @param myTree
     */
    private static void last(MyTree myTree) {
        if (myTree == null) {
            return;
        }
        last(myTree.left);
        last(myTree.right);
        System.out.println(myTree);
    }


    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        MyTree myTreeLeft = new MyTree();
        MyTree myTreeRight = new MyTree();
        MyTree myTreeLeftLeft = new MyTree();
        MyTree myTreeRightRight = new MyTree();
        myTree.left = myTreeLeft;
        myTree.right = myTreeRight;
        myTreeLeft.left = myTreeLeftLeft;
        myTreeLeft.right = myTreeRightRight;

        myTree.value = "a";
        myTree.left.value = "b";
        myTree.right.value = "c";
        myTree.left.left.value = "d";
        myTree.left.right.value = "e";
        System.out.println(myTree);
        System.out.println("前序遍历：");
        before(myTree);
        System.out.println("中序遍历：");
        middle(myTree);
        System.out.println("后序遍历：");
        last(myTree);

    }
}
