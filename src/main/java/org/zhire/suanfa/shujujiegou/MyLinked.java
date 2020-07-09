package org.zhire.suanfa.shujujiegou;

import org.junit.Test;

import java.util.Objects;

/**
 * 实现单链表，支持增删操作
 * （实现循环链表、双向链表，支持增删操作----未完成）
 * 实现单链表反转
 * 实现两个有序的链表合并为一个有序链表
 * 实现求链表的中间结点
 * 判断字符串是不是回文字符串
 * 删除倒数第N个元素
 * 查询第N个元素
 *
 * @Author: chenqi
 * @Date: 2020.7.6 16:55
 */
public class MyLinked<E> {
    // 链表初始化的长度
    private int size = 0;
    // 链表头节点
    private Node<E> first;
    // 链表尾节点
    private Node<E> last;

    public MyLinked() {

    }

    class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, next);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinked<?> myLinked = (MyLinked<?>) o;
        return size == myLinked.size &&
                Objects.equals(first, myLinked.first) &&
                Objects.equals(last, myLinked.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, first, last);
    }

    @Override
    public String toString() {
        return "MyLinked{" +
                "size=" + size +
                ", first=" + first +
                ", last=" + last +
                '}';
    }


    /**
     * 尾插法
     *
     * @param e
     */
    private void add(E e) {
        Node<E> eNode = new Node<>(e);
        if (first == null) {
            first = eNode;
            last = eNode;
        } else {
            last.next = eNode;
            last = eNode;
        }
        size++;
    }


    /**
     * 头插法
     *
     * @param e
     */
    private void addInFirst(E e) {
        Node<E> eNode = new Node<>(e);
        // 这样写也行
        // eNode.next = first;
        // first = eNode;

        if (first == null) {
            first = eNode;
            last = eNode;
        } else {
            Node<E> firstNext = first;
            first = eNode;
            first.next = firstNext;
        }
        size++;
    }


    /**
     * 删除指定位置元素
     *
     * @param index
     */
    private void delete(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("索引异常");
        }
        // 如果删除得是头结点
        if (index == 0) {
            first = first.next;
            //  first.next = first;
            // 如果删除得是尾节点
        } else if (index == (size - 1)) {
            Node preNode = selectByIndex(index - 1);
            last = preNode;
            last.next = null;

        } else {
            Node node = selectByIndex(index);
            Node nextNode = node.next;
            Node preNode = selectByIndex(index - 1);
            preNode.next = nextNode;
        }

        size--;

    }


    /**
     * 查找指定位置元素
     *
     * @param index 索引
     * @return
     */
    private Node selectByIndex(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("索引异常");
        }
        Node result = first;
        if (result == null) {
            return null;
        }
        // result = result.next 这一步必须的，不然查到数据永远是第一条数据
        for (int i = 0; i < size; i++, result = result.next) {
            if (index == i) {
                return result;
            }
        }
        return null;
    }


    /**
     * 查找指定元素是否存在
     *
     * @param e
     * @return
     */
    private Node selectByValue(E e) {
        Node result = first;
        if (result == null) {
            return null;
        } else {
            for (int i = 0; i < size; i++, result = result.next) {
                System.out.println("e:" + e + "value:" + result.value);
                if (result == e || result.value.equals(e)) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 链表反转
     *
     * @param myLinked
     * @return
     */
    private MyLinked reverseLinkedList(MyLinked myLinked) {
        Node result = myLinked.first;
        if (result == null) {
            return null;
        }
        MyLinked<Object> addLinkedReverseLinkedList = new MyLinked<>();
        for (int i = 0; i < size; i++, result = result.next) {
            System.out.println(result.value);
            addLinkedReverseLinkedList.addInFirst(result.value);
        }
        return addLinkedReverseLinkedList;
    }

    /**
     * 链表排序
     * 注:实现方法比较low，因为每次插入时会生成新的链表，重新插入排序，暂时写这么写
     *
     * @param e
     * @return
     */
    private MyLinked addBySort(Integer e) {
        Node node = new Node<>(e);
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
        Node result = first;
        int[] array = new int[size];
        // 把原来的链表值放入数组
        for (int i = 0; i < size; i++, result = result.next) {
            array[i] = (Integer) result.value;
        }
        // 排序
        int[] sort = sort(array);
        // 创建新的链表，进行插入，插入的是排好序的数组
        MyLinked myLinked = new MyLinked();
        for (int i = 0; i < sort.length; i++) {
            myLinked.add(sort[i]);
        }
        System.out.println();
        return myLinked;
    }


    /**
     * 选择排序
     *
     * @param array
     * @return
     */
    private int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[i]) {
                    min = j;
                }
            }
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
        return array;
    }


    /**
     * 两个有序链表合并成一个有序链表
     *
     * @param one
     * @param two
     * @return
     */
    public MyLinked mergeSortLinked(MyLinked one, MyLinked two) {
        Node oneNode = one.first;
        Node twoNode = two.first;
        int[] array = new int[one.size + two.size];
        int i = 0;
        for (; i < one.size; i++, oneNode = oneNode.next) {
            array[i] = (Integer) oneNode.value;
        }
        for (int j = 0; j < two.size; j++, twoNode = twoNode.next) {
            array[i] = (Integer) twoNode.value;
            i++;
        }
        // 排序
        int[] sort = sort(array);

        // 创建新的链表
        MyLinked myLinked = new MyLinked();
        for (int k = 0; k < sort.length; k++) {
            myLinked.add(sort[k]);
        }
        return myLinked;
    }

    /**
     * 删除倒数第N个元素
     *
     * @param index
     */
    private void deleteLinked(int index) {
        Node result = first;
        // 算出正数的索引位置
        index = size - index;
        System.out.println("index：" + index);
        for (int i = 0; i < size; i++, result = result.next) {
            if (index == i) {
                Node preNode = selectByIndex(index - 1);
                Node nextNode = selectByIndex(index + 1);
                preNode.next = nextNode;
            }
        }
        size--;
    }


    /**
     * 判断字符串是回文字符串
     * 时间复杂度 O(n)
     *
     * @param str
     * @return
     */
    private boolean palindromeString(String str) {
        char[] array = str.toCharArray();
        MyLinked lastInsertLinked = new MyLinked();
        MyLinked firstInsertLinked = new MyLinked();
        for (int i = 0; i < array.length; i++) {
            lastInsertLinked.add(array[i]);
            firstInsertLinked.addInFirst(array[i]);
        }
        if (lastInsertLinked.equals(firstInsertLinked)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        MyLinked myLinked = new MyLinked();
//        // 尾插法插入
//        myLinked.add(0);
//        myLinked.add(4);
//        myLinked.add(6);
//        myLinked.add(8);
//        myLinked.add(9);
//        System.out.println(myLinked);

        // 头插法插入
        // myLinked.addInFirst(4);
        // myLinked.addInFirst(5);
        // myLinked.addInFirst(6);
        // System.out.println(myLinked);
        // System.out.println(myLinked.selectByIndex(0));
        // myLinked.delete(2);
        // System.out.println(myLinked);
        // System.out.println(myLinked.selectByValue(6));

        // 链表反转
        // System.out.println(myLinked.reverseLinkedList(myLinked));

        // 插入排序
        // myLinked.addBySort(1);
        // myLinked.addBySort(3);
        // myLinked.addBySort(5);
        // myLinked.addBySort(4);
        // MyLinked linked = myLinked.addBySort(0);
        // System.out.println(linked);

        MyLinked myLinked2 = new MyLinked();
        myLinked2.add(1);
        myLinked2.add(2);
        myLinked2.add(3);
        myLinked2.add(4);
        myLinked2.add(5);
        System.out.println(myLinked2);

        // 删除倒数第N个数据
        myLinked2.deleteLinked(2);
        System.out.println(myLinked2);


    }

    /**
     * 两个有序链表排序
     */
    @Test
    public void test() {
        MyLinked myLinked = new MyLinked();
        // 尾插法插入
        myLinked.add(0);
        myLinked.add(4);
        myLinked.add(6);
        myLinked.add(8);
        myLinked.add(9);
        System.out.println(myLinked);
        MyLinked myLinked2 = new MyLinked();
        myLinked2.add(3);
        myLinked2.add(18);
        myLinked2.add(30);
        myLinked2.add(18);
        System.out.println(myLinked2);
        MyLinked mergeSortLinked = mergeSortLinked(myLinked, myLinked2);
        System.out.println(mergeSortLinked);
    }

    /**
     * 判断字符串是回文字符串
     */
    @Test
    public void test2() {
        String s = "123454321";
        System.out.println(palindromeString(s));
    }
}

