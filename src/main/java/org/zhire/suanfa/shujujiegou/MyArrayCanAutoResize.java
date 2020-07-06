package org.zhire.suanfa.shujujiegou;


/**
 * 1。自定义数组--支持动态扩容
 *
 * @Author: chenqi
 * @Date: 2020.7.6 09:00
 */
public class MyArrayCanAutoResize<T> {

    // 定义整型数据data保存数据
    private T[] date;
    // 数组的实际长度
    private int size;

    /**
     * 数组的初始化
     *
     * @param length
     */
    public MyArrayCanAutoResize(int length) {
        date = (T[]) new Object[length];
        this.size = 0;
    }

    /**
     * 无参构造，默认大小10
     */
    public MyArrayCanAutoResize() {
        this(10);
    }

    /**
     * 数组打印
     */
    public void printMyArray() {
        for (int i = 0; i < date.length; i++) {
            System.out.println(date[i]);
        }
        System.out.println();
    }


    /**
     * 数组的插入
     *
     * @param index 索引
     * @param value 值
     */
    public void add(int index, T value) {
        if (index > size || index < 0) {
            throw new RuntimeException("索引异常，位置不合法");
        }
        date[index] = value;
        this.size++;
        if (size == date.length) {
            resize((T[]) date);
        }
    }


    public void resize(T[] date) {
        // 下面的是错误写法 数组扩容不能直接更改原来数组大小
//         this.date = (T[]) new Object[size * 2];
//         this.date = (T[]) date;

        T[] newArray = (T[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = date[i];
        }
        this.date = newArray;
    }

    /**
     * 数组的删除
     *
     * @param index 索引
     */
    public void remove(int index) {

    }

    /**
     * 数组的更新
     *
     * @param index  索引
     * @param numbre 值
     */
    public void update(int index, int numbre) {

    }

    /**
     * 数组的查找
     *
     * @param index 索引
     * @return
     */
    public int find(int index) {
        return 0;
    }

    public static void main(String[] args) {
        MyArrayCanAutoResize myArray = new MyArrayCanAutoResize(3);
        myArray.add(0, 1);
        myArray.add(1, 2);
        myArray.add(2, 3);
        myArray.add(3, 4);
        myArray.add(4, 5);
        myArray.add(5, 6);
        myArray.add(6, 7);
        myArray.printMyArray();


    }
}
