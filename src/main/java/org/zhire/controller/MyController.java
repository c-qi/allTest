package org.zhire.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhire.config.MyException;
import org.zhire.pojo.User;
import org.zhire.wechat.WxService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Date 2020/11/18 21:30
 * @Author by chenqi
 */
@Controller("yyy")
@RequestMapping("/yyy")
public class MyController {
    @Autowired
    private WxService wxService;

    @GetMapping(value = "/test2")
    public Map<String, Object> test222() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<User> list = CollUtil.newArrayList();
        User user = new User();
        user.setName("cq");
        user.setManagerId(12345678987654321L);
        list.add(user);
        map.put("list", list);
        System.out.println(JSON.toJSONString(map));
        return map;
    }

    @PostMapping(value = "/send")
    String subscribeSend(HttpServletRequest request) {
        wxService.push();
        return "Ok";
    }

    @RequestMapping(value = "/ttt")
    String tt() {
        String name = "";
        Preconditions.checkArgument(StringUtils.isNotEmpty(name), "neme为空");
        return "OK";
    }

    @RequestMapping(value = "/tttt")
    String ttt() {
        String name = "";
        if (name.length() == 0) throw new MyException(9999, "neme为空");
        return "OK";
    }

    public static void main(String[] args) {
        Optional.ofNullable(null).ifPresent(System.out::println);

    }
}
