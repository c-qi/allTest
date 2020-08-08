package org.zhire.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/userTest")
public class MyUserController {
    @Autowired
    private MyUserService myUserService;

    @RequestMapping("/insert")
    public String insert() {
        myUserService.insert();
        return "ok";
    }

    @RequestMapping("/findName")
    public User insert(@RequestParam String userName) {
        return myUserService.findByUserName(userName);
    }

    @RequestMapping("/findByUserNameOrEmail")
    public User findByUserNameOrEmail(@RequestParam(required = false) String userName, @RequestParam(required = false) String email) {
        return myUserService.findByUserNameOrEmail(userName, email);
    }
    @RequestMapping("/findAll")
    public List<User> findAll(@RequestParam int page , @RequestParam int pageSize) {
        return myUserService.findAll(page, pageSize);
    }

    @RequestMapping("/findAllInfo")
    public List<UserAndInfo> findAllInfo(@RequestParam int page , @RequestParam int pageSize) {
        return myUserService.findAllInfo(page, pageSize);
    }
}
