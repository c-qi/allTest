package org.zhire.demo.spring.aop;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Service;
import org.zhire.demo.spring.ioc.IOCUser;

@Service
public class AOPServiceImpl implements AOPService {
    @Override
    public void printUser(IOCUser user) {
        if (StringUtils.isEmpty(user.getName())) {
            throw new RuntimeException("name is not null!");
        }
        System.out.println(user);
    }
}
