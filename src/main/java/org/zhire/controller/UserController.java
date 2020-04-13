package org.zhire.controller;

import org.zhire.pojo.User;
import org.zhire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUser/{name}")
    public List<User> findUser(@PathVariable String name) {
        List<User> userList = userService.findName(name);
        return userList;
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        List<User> userList = userService.findAll();
        return userList;
    }

    @RequestMapping("/login")
    public String login(String name, String pass, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = userService.login(name, pass);
        if (user == null) {
            return "账户名或密码错误！";
        } else {
            // request.getRequestDispatcher("/admin/main.html").forward(request, response);
            request.getSession().setAttribute("loginUser", user);
            response.sendRedirect("/admin/main.html");
        }
        return "ok";
    }


}
