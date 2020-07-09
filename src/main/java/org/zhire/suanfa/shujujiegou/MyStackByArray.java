package org.zhire.suanfa.shujujiegou;

import java.util.Arrays;

/**
 * 数组实现栈-入栈出栈
 *
 * @Author: chenqi
 * @Date: 2020.7.9 15:18
 */
public class MyStackByArray {
    // 数据存储
    private Object[] array;
    // 数组大小
    private int size;
    // 数组数据大小
    private int count;

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MyStackByArray{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", count=" + count +
                '}';
    }

    public MyStackByArray(int size) {
        this.array = new Object[size];
        this.size = size;
        this.count = 0;
    }


    /**
     * 入栈操作
     *
     * @param s
     */
    private void push(String s) {
        if (count >= size) {
            throw new RuntimeException("array已满，无法继续存储");
        }
        if (count == 0) {
            array[0] = s;
        } else {
            array[count] = s;
        }
        count++;
    }

    /**
     * 出栈操作
     */
    private void pop() {
        String result = (String) array[count - 1];
        array[count - 1] = null;
        count--;
        System.out.println(result);

    }

    public static void main(String[] args) {
        MyStackByArray stack = new MyStackByArray(5);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.push("6");
        System.out.println(stack);
    }
}
