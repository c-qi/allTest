package org.zhire.suanfa.shujujiegou;

import lombok.Data;


/**
 * 队列--链表实现
 *
 * @Author: chenqi
 * @Date: 2020.7.10 15:33
 */
@Data
public class MyQueueByLinked {
    private Node first; // 队列头
    private Node last; // 队列尾
    private int count = 0; // 当前队列的大小

    private MyQueueByLinked() {
    }

    class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }


    /**
     * 向队列添加元素尾插法
     *
     * @param obj
     */
    private void push(Object obj) {
        Node node = new Node(obj);
        if (first == null) {
            this.first = node;
            this.last = node;
        } else {
            last.next = node;
            last = node;
        }
        count++;
    }

    /**
     * 取出队列，从头取
     *
     * @return
     */
    private Object pop() {
        if (first == null) {
            return null;
        } else {
            Node firstNode = first;
            Node nextNode = first.next;
            first = nextNode;
            count--;
            // 处理出最后一个元素的情况
            if (first == null) {
                last = null;
            }
            return firstNode.value;
        }

    }

    @Override
    public String toString() {
        return "MyQueueByLinked{" +
                "first=" + first +
                ", last=" + last +
                ", count=" + count +
                '}';
    }

    public static void main(String[] args) {
        MyQueueByLinked queue = new MyQueueByLinked();
        queue.push("1");
        queue.push("2");
        queue.push("3");
        queue.push("4");
        queue.push("5");
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue);
        queue.push("6");
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue);
        queue.push("7");
        queue.push("8");
        queue.push("9");
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue);
        queue.push("10");
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue);

    }

}
