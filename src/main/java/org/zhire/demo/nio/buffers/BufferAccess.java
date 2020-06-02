package org.zhire.demo.nio.buffers;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * NIO：
 * Buffer	Channel		Selector
 * <p>
 * 一个 Buffer 本质上是内存中的一块， 可以将数据写入这块内存， 从这块内存获取数据
 * <p>
 * Buffer三大核心概念：position、limit、capacity
 * capacity：它代表这个缓冲区的容量，一旦设定就不可以更改。比如 capacity 为 1024 的 IntBuffer，代表其一次可以存放 1024 个 int 类型的值。
 * 一旦 Buffer 的容量达到 capacity，需要清空 Buffer，才能重新写入值
 * 从写操作模式到读操作模式切换的时候（flip），position 都会归零，这样就可以从头开始读写了。
 * 写操作模式下，limit 代表的是最大能写入的数据，这个时候 limit 等于 capacity。
 * 写结束后，切换到读模式，此时的 limit 等于 Buffer 中实际的数据大小，因为 Buffer 不一定被写满了。
 *
 * position：buffer当前所在的操作位置
 * limit：buffer最大的操作位置
 * capacity：buffer的最大长度
 */
public class BufferAccess {
    public static void main(String[] args) {
        // buffer创建
        ByteBuffer buffer = ByteBuffer.allocate(10);
        printBuffer(buffer);

        buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) '0');
        printBuffer(buffer);

        // 写操作模式到读操作模式切换
        buffer.flip();
        printBuffer(buffer);

        // 取buffer
        System.out.println("" + (char) buffer.get() + (char) buffer.get());
        printBuffer(buffer);

        buffer.mark();
        printBuffer(buffer);

        // 读取两个元素后，恢复到之前mark的位置处
        System.out.println("" + (char) buffer.get() + (char) buffer.get());
        printBuffer(buffer);

        buffer.reset();
        // buffer.rewind();

        printBuffer(buffer);


        buffer.compact();
        printBuffer(buffer);


        buffer.clear();
        printBuffer(buffer);

    }

    private static void printBuffer(Buffer buffer) {
        System.out.println("[limit=" + buffer.limit()
                + ", position = " + buffer.position()
                + ", capacity = " + buffer.capacity()
                + ", array = " + buffer.toString() + "]");
    }
}
