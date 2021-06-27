package org.zhire.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhire.model.UserDTO2;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2021/6/23 10:11 上午
 * @Author chenqi
 */
@Controller
@RequestMapping("/mvcTest")
public class MvcController {

    @RequestMapping("/getUsers")
    private String test(Model model) {
        Map<String, Object> map = Maps.newHashMap();
        List<UserDTO2> list = Lists.newArrayList();
        list.add(UserDTO2.of().setId(1L).setName("cq").setTimestamp(new Timestamp(System.currentTimeMillis())));
        list.add(UserDTO2.of().setId(2L).setName("cq2").setTimestamp(new Timestamp(System.currentTimeMillis())));
        map.put("list", list);
        map.put("total", 10);
        model.addAttribute("form", map);
        return "/static/user.jsp";
    }
}
