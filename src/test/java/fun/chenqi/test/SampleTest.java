package fun.chenqi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zhire.SpringBootStart;
import org.zhire.controller.TestController;
import org.zhire.mapper.UserMapper;
import org.zhire.pojo.User;

import java.util.List;

/**
 * @Author: chenqi
 * @Date: 2019.4.15 16:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class SampleTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestController testController;

    @Test
    public void testSelect() {
        User user = new User();
        user.setName("cw");
        user.setPassword("cw");
        testController.insert(user);
    }

}
