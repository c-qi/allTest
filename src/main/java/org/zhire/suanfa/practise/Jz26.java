package org.zhire.suanfa.practise;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * @Date 2020/11/4 11:16
 * @Author by chenqi
 */
public class Jz26 {
    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        List<String> list1 = getTree(A, "");
        ArrayList<String> list = new ArrayList<>(list1);
        this.list.clear();
        List<String> list2 = getTree(B, "");
        System.out.println(list);
        System.out.println(list2);
        if (list2.size() > list.size()) {
            return false;
        }
        for (String s : list2) {
            for (String s1 : list) {
                if (s1.contains(s)){
                    return true;
                }
            }
        }
        return false;
    }

    List<String> list = new ArrayList();

    List<String> getTree(TreeNode A, String s) {
        if (A == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append(s).append(A.val);
        if (A.left == null && A.right == null) {
            list.add(sb.toString());
            return list;
        }
        getTree(A.left, sb.toString());
        getTree(A.right, sb.toString());
        return list;
    }

    /**
     * A = [3,4,5,1,2], B = [4,1]
     */
    @Test
    public void t() {
        TreeNode node = new TreeNode(1);
        TreeNode nodeLeft = new TreeNode(2);
        TreeNode nodeRight = new TreeNode(3);
        TreeNode nodeLeftLeft = new TreeNode(4);
        TreeNode nodeLeftRight = new TreeNode(5);
        node.left = nodeLeft;
        node.right = nodeRight;
        nodeLeft.left = nodeLeftLeft;
        nodeLeft.right = nodeLeftRight;
        TreeNode node2 = new TreeNode(4);
        //TreeNode nodeLeft2 = new TreeNode(-4);
       // node2.left = nodeLeft2;
        System.out.println(node);
        System.out.println(node2);
        System.out.println(isSubStructure(node, node2));

    }
}
