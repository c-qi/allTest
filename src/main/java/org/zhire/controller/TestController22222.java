package org.zhire.controller;


import org.zhire.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users2")
@Controller
public class TestController22222 {

    @PostMapping(value = "/test")
    public User test(@RequestHeader(value = "id", required = false)String id, @RequestBody User[] user) {
        System.out.println(id);
        if (null == id){
            System.out.println("nulllllll");
        }
        for (User user1 : user) {
            System.out.println(user1.toString());
        }
        return new User();
    }

    public static void main(String[] args) {
        String s = "xiaomi";
        if (s.contains("公司")){
            s = s + "评测";
            System.out.println(s);
        }else {
            s = s + " 公司评测";
            System.out.println(s);
        }

    }
}
