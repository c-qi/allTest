package org.zhire.jvm;

import org.zhire.pojo.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: chenqi
 * @Date: 2019.8.30 09:41
 */
public class GcTest {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GcTest gcTest = (GcTest) o;
        return Arrays.equals(b, gcTest.b);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(b);
    }

    byte[] b = new byte[100 * 1024];

    public static void main(String[] args) {
        List list = new ArrayList();
        while (true) {
            list.add(new User());
        }
    }

    @Test
    public void sdddd() {
        String s2 = new String("abc");
        String s3 = new String("abc");

        System.out.println(s2 == s3);

    }
}
