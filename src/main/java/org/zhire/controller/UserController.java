package org.zhire.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhire.config.MyException;
import org.zhire.model.UserDTO;
import org.zhire.pojo.User;
import org.zhire.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @PostMapping("/findUser2")
    public String findUser2(String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping("/findUser3")
    public String findUser3(@RequestParam String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping("/findUser4")
    public String findUser4(@RequestBody(required = false) String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping("/findUser5")
    public String findUser4(@RequestBody Object request) {
        System.out.println(request);
        return request + "";
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

    @RequestMapping("/findTest")
    public UserDTO findTest(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        System.out.println(JSON.toJSONString(userDTO));
        if (bindingResult.hasErrors())
            throw new RuntimeException("缺少必传字段");
        UserDTO u = new UserDTO()
                .setName("cq")
                .setPassword("23424");
        return u;
    }

    @RequestMapping("/findListDTO")
    public List<UserDTO> findListDTO(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        System.out.println(JSON.toJSONString(userDTO));
        if (bindingResult.hasErrors())
            throw new MyException(99999, "缺少必传字段");
        return userService.findListDTO();
    }

}
