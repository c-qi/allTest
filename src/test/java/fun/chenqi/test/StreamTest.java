package fun.chenqi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.zhire.SpringBootStart;
import org.zhire.cloudstream.StreamPut;
import org.zhire.cloudstream.output.Send;
import org.zhire.mapper.UserMapper;
import org.zhire.pojo.User;

import java.util.List;

/**
 * @Author: chenqi
 * @Date: 2019.4.15 16:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class StreamTest {
//    @Autowired
//    private Send send;

    @Autowired
    private StreamPut streamPut;
    @Test
    public void testSelect() {
        String mess = "asdsad";
        Message message = MessageBuilder
                .withPayload(mess.getBytes())
                .build();
        streamPut.output().send(message);
    }

}
