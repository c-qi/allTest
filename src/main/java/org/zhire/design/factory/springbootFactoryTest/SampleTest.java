package org.zhire.design.factory.springbootFactoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zhire.SpringBootStart;

/**
 * @Author: chenqi
 * @Date: 2019.4.15 16:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class SampleTest {

    @Test
    public void testSelect() {
        System.out.println(HandelFactory.getHandel(1).handel());
        System.out.println(HandelFactory.getHandel(2).handel());
        System.out.println(HandelFactory.getHandel(3).handel());
    }

}
