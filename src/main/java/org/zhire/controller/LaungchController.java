package org.zhire.controller;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhire.demo.spring.ioc.IOCUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2021/4/12 17:12
 * @Author by chenqi
 */
@Slf4j
@RestController
@RequestMapping("/la")
public class LaungchController {

    @GetMapping(value = "/en")
    public Map<Object, Object> en() {
        List<IOCUser> userList = CollUtil.newArrayList();
        IOCUser iocUser = new IOCUser();
        iocUser.setAddress("河南省");
        IOCUser iocUser2 = new IOCUser();
        iocUser2.setAddress("北京市");
        userList.add(iocUser);
        userList.add(iocUser2);
        HashMap<Object, Object> map = CollUtil.newHashMap();
        map.put("code", 200);
        map.put("data", userList);
        log.info("----我睡觉了----");
        try {
            Thread.sleep(1000 * 60 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("----我起来了----");

        return map;
    }

}
