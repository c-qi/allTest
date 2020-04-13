package org.zhire.jvm;

import org.zhire.pojo.User;
import org.junit.Test;

import java.util.*;

/**
 * @Author: chenqi
 * @Date: 2020.1.17 15:15
 */
public class OOMTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        while (true) {
            map.put(UUID.randomUUID().toString(), new User(UUID.randomUUID().toString()));
            System.out.println(map);
        }
    }
    @Test
    public void testOOM() {
//        for (int i = 0; i < 10000000; i++) {
//            test(i);
//        }

        Map map = new HashMap();
        while (true) {
            map.put(UUID.randomUUID().toString(), new User(UUID.randomUUID().toString()));
            System.out.println(map);
        }
    }

    public void test(int i) {
        test(i);
    }
}
