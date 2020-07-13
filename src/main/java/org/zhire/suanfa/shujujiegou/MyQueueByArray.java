package org.zhire.suanfa.shujujiegou;

import lombok.Data;

import java.util.Arrays;

/**
 * 队列--数组实现
 *
 * @Author: chenqi
 * @Date: 2020.7.10 15:33
 */
@Data
public class MyQueueByArray {
    private Object[] value; // 存放的值
    private int head; // 队列的头索引 初始化为0
    private int last; // 队列的尾索引 初始化0
    private int size; // 队列的大小 根据传入的值初始化
    private int count; // 当前队列的大小 初始化0

    public MyQueueByArray() {
    }

    public MyQueueByArray(int size) {
        this.value = new Object[size];
        this.head = 0;
        this.last = 0;
        this.size = size;
        this.count = 0;
    }

    /**
     * 向队列添加元素 非循环队列 会涉及到数据移动
     *
     * @param obj
     */
    private void push(Object obj) {
        if (count >= size) { // 处理越界情况
            throw new RuntimeException("queue is full");
        } else if (last + 1 == value.length) { // 处理 null,null,1,2,3 这时候数组满了，加入新数据4，就要变成 1,2,3,4,null
            int flag = head; // 记录head头的初始索引
            for (int i = 0; i < size; i++) { // 遍历之前的数组
                if (flag <= last) {  // 避免head++之后值超过size
                    value[i] = value[flag];
                    value[flag] = null;
                }
                flag++;
            }
            // 数组变成类似1,2,3,4,null后需要修改队列头尾的索引
            head = 0;
            last = count;
            value[count] = obj;
        } else {
            // 当前位置有值跳过 放到队列尾处
            if (value[count] != null) {
                value[last + 1] = obj;
                last = last + 1;
            } else {
                value[count] = obj;
                last = count;
            }
        }
        count++;
    }


    /**
     * 向队列添加元素 循环队列 不会移动数据
     *
     * @param obj
     */
    private void push2(Object obj) {
        if (count >= size) { // 处理越界情况
            throw new RuntimeException("queue is full");
        } else if (last + 1 == value.length) { // 处理 null,null,1,2,3 这时候数组满了，加入新数据4，就要变成 4,null,1,2,3,
            if (value[0] != null) {
                last++;
                value[last] = obj;
            } else {
                last = 0;
                value[last] = obj;
            }
        } else {
            // 当前位置有值跳过 放到队列尾处
            if (value[count] != null) {
                value[last + 1] = obj;
                last = last + 1;
            } else {
                value[count] = obj;
                last = count;
            }
        }
        count++;
    }

    /**
     * 取元素
     *
     * @return
     */
    private Object pop() {
        if (count == 0) {
            return null;
        }
        Object result = value[head];
        value[head] = null;
        head++;
        count--;
        return result;

    }


    @Override
    public String toString() {
        return "MyQueueByArray{" +
                "value=" + Arrays.toString(value) +
                ", head=" + head +
                ", last=" + last +
                ", size=" + size +
                ", count=" + count +
                '}';
    }

    public static void main(String[] args) {
        MyQueueByArray queue = new MyQueueByArray(5);
        // 正常队列测试
//        queue.push("1");
//        queue.push("2");
//        queue.push("3");
//        queue.push("4");
//        queue.push("5");
//        System.out.println(queue);
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//        System.out.println(queue);
//        queue.push("6");
//        System.out.println(queue);
//        System.out.println(queue.pop());
//        System.out.println(queue);
//        queue.push("7");
//        System.out.println(queue);
//        queue.push("8");
//        queue.push("9");
//        System.out.println(queue);

        // 循环链表测试
        queue.push2("1");
        queue.push2("2");
        queue.push2("3");
        queue.push2("4");
        queue.push2("5");
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue);
        queue.push2("6");
        System.out.println(queue);
        queue.push2("7");
        System.out.println(queue);
        queue.push2("8");
        System.out.println(queue);

        System.out.println(queue.pop());
        System.out.println(queue);
        queue.push2("9");
        System.out.println(queue);
    }

}
