package org.zhire.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.zhire.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/users")
public class TestController {

    @GetMapping(value = "/test")
    public User test() {
        User user = new User();
        user.setName("chenqi");
        user.setPassword("1121");
        return user;
    }

    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (name.equals("test") && pwd.equals("test123")) {
            session.setAttribute("user", name);
            return "登录成功";
        } else {
            return "用户名或密码错误!";
        }
    }

    @RequestMapping("/s")
    public String testwork() {
        String s = "{" +
                "    \"name\": \"陈琦\"" +
                "}";
        // String s = "{'name':'陈琦'}";
        return s;
    }

    @RequestMapping("/j")
    public JSONObject j() {
        JSONObject j = new JSONObject();
        j.put("name", "陈琦");
        return j;
    }

    @RequestMapping("/js")
    public String js() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", "chernww");
        Object parse = JSON.parse(String.valueOf(jsonObject));
        //String s ="{\"key\":\"陈琦\"}";
        System.out.println(parse);
        String s1 = JSON.toJSONString(parse);
        System.out.println(s1);
        return s1;
    }

    @RequestMapping("/jss")
    public JSONObject js(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        return jsonObject;
    }

    @RequestMapping("/date")
    public Date date(@RequestParam Date date) {
        System.out.println(date);
        return date;
    }
}
