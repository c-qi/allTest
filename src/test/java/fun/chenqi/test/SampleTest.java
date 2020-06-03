package fun.chenqi.test;

import org.zhire.SpringBootStart;
import org.zhire.mapper.UserMapper;
import org.zhire.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
