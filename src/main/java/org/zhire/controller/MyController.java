package org.zhire.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zhire.pojo.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/11/18 21:30
 * @Author by chenqi
 */
@Controller("/yyy")
public class MyController {

    @GetMapping(value = "/test2")
    public Map<String,Object> test222() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<User> list = CollUtil.newArrayList();
        User user = new User();
        user.setName("cq");
        user.setManagerId(12345678987654321L);
        list.add(user);
        map.put("list",list);
        System.out.println(JSON.toJSONString(map));
        return map;
    }
}
