package org.zhire.suanfa.shujujiegou;


import org.junit.Test;

/**
 * 链表实现栈-入栈出栈
 * 栈实现浏览器的前进后退功能
 *
 * @Author: chenqi
 * @Date: 2020.7.9 15:18
 */
public class MyStackByLinked {

    private Node first;
    private Node last;
    private int count;

    public MyStackByLinked() {
        this.count = 0;
    }

    class Node {
        private String value;
        private Node next;

        public Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyStackByLinked{" +
                "first=" + first +
                ", last=" + last +
                ", count=" + count +
                '}';
    }

    /**
     * 入栈操作
     * 尾插法插入链表
     * 时间复杂度O(1)
     *
     * @param s
     */
    private void push(String s) {
        Node node = new Node(s, null);
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        this.count++;
    }

    /**
     * 出栈操作
     * 时间复杂度O(n)
     */
    private String pop() {
        // 处理链表为空的情况
        if (first == null) {
            return null;
        } else {
            String value = null;
            // 单独处理链表只有一个元素的情况
            if (count == 1) {
                value = first.value;
                first = null;
                last = null;
                this.count--;
                return value;
            }
            value = this.last.value;
            Node preValue = this.first;
            // 遍历元素，找到最后的元素和倒数第二的元素，让倒数第二的元素变为最后一个元素
            for (int i = 0; i < count; i++, preValue = preValue.next) {
                if (i == count - 2) {
                    last = preValue;
                    last.next = null;
                    this.count--;
                    return value;
                }
            }
        }
        return null;

    }

    MyStackByLinked goLinked = null;
    MyStackByLinked backLinked = null;

    /**
     * 浏览器前进操作
     *
     * @param s
     * @param isNew
     */
    private void goWebsite(String s, boolean isNew) {
        if (goLinked == null) {
            goLinked = new MyStackByLinked();
        }
        // 点击前进，插入数据
        if (null != s && !isNew) {
            System.out.println("前进栈插入数据：" + s);
            goLinked.push(s);
        } else if (null != s && isNew) {
            // 处理这种情况
            // 前进栈 a-b-c-d-e 再后退，后退栈变成 e-d 前进栈变成a-b-c 此时打开新的页面 f
            // 则 前进栈变成 a-b-c-f 后退栈为空
            goLinked.push(s);
            backLinked = null;

        } else {
            // 点击前进，但是没有插入数据，这个操作是先点后退，再点前进，这个时候是没有插入数据的
            String lastValue = backLinked.last.value;
            backLinked.pop();
            System.out.println("没有插入前进数据，点击前进：" + lastValue);
            goLinked.push(lastValue);
            System.out.println("没有插入前进数据，正在浏览：" + goLinked.last.value);
        }
    }


    /**
     * 浏览器后退操作
     */
    private void backWebsite() {
        String pop = goLinked.pop();
        System.out.println("点击后退，弹出前进栈栈顶元素：" + pop);
        if (backLinked == null) {
            backLinked = new MyStackByLinked();
        }
        backLinked.push(pop);
        System.out.println("点击后退，弹出前进栈栈顶元素插入后退栈");
        System.out.println("点击后退后，正在浏览：" + goLinked.last.value);
    }

    /**
     * 测试浏览器前进后退
     */
    @Test
    public void testGoAndBackWebsite() {
        goWebsite("a", false);
        goWebsite("b", false);
        goWebsite("c", false);
        goWebsite("d", false);
        goWebsite("f", false);
        System.out.println("前进栈：" + goLinked);
        System.out.println("后退栈：" + backLinked);
        backWebsite();
        System.out.println("前进栈：" + goLinked);
        System.out.println("后退栈：" + backLinked);
        goWebsite(null, false);
        System.out.println("前进栈：" + goLinked);
        System.out.println("后退栈：" + backLinked);
        backWebsite();
        backWebsite();
        System.out.println("前进栈：" + goLinked);
        System.out.println("后退栈：" + backLinked);
        goWebsite("f", true);
        System.out.println("前进栈：" + goLinked);
        System.out.println("后退栈：" + backLinked);
    }

    public static void main(String[] args) {
        MyStackByLinked linked = new MyStackByLinked();
        linked.push("a");
        linked.push("b");
        linked.push("c");
        linked.push("d");
        linked.push("e");
        System.out.println(linked);
        System.out.println(linked.pop());
        System.out.println(linked.pop());
        System.out.println(linked.pop());
        System.out.println(linked.pop());
        System.out.println(linked.pop());
        System.out.println(linked);
        linked.push("f");
        System.out.println(linked);
    }
}
