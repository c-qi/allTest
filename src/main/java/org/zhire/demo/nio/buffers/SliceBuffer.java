package org.zhire.demo.nio.buffers;// $Id$

import java.nio.*;

public class SliceBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);


        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        System.out.println(buffer.position(3));
        System.out.println(buffer.limit(7));

        ByteBuffer slice = buffer.slice();
        System.out.println(slice);
        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 11;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }
}
