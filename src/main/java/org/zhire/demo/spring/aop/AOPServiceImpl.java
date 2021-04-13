package org.zhire.demo.spring.aop;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.stereotype.Service;
import org.zhire.demo.spring.ioc.IOCUser;

import java.util.List;

@Service
public class AOPServiceImpl implements AOPService {
    @Override
    public List<IOCUser>  printUser(IOCUser user) {
        if (StringUtils.isEmpty(user.getName())) {
            throw new RuntimeException("name is not null!");
        }
        List<IOCUser> list = CollUtil.newArrayList();
        for (int i = 0; i < 20; i++) {
            IOCUser iocUser = new IOCUser();
            iocUser.setId(i);
            iocUser.setName(user.getName());
            list.add(iocUser);
        }
        System.out.println(JSON.toJSONString(list));
        return list;
    }
}
