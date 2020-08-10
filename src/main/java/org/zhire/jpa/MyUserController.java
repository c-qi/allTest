package org.zhire.jpa;

import io.swagger.annotations.ApiOperation;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @Api(value = "jpa-API", description = "jpa") swagger的访问 // http://127.0.0.1:7971/swagger-ui.html#/
@Api(name = "jpa-AP", description = "jpa") // jsondoc的访问 http://localhost:7971/jsondoc-ui.html# 输入 http://localhost:7971/jsondoc
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
   // @ApiOperation(value = "查询用户",notes = "返回根据名称查询用户") // swagger 注解
    @ApiMethod(summary = "查询用户", description = "返回根据名称查询用户") // jsonDoc 注解
    public User insert(@ApiQueryParam(name = "userName", description = "用户名称") @RequestParam String userName) {
        return myUserService.findByUserName(userName);
    }

    @RequestMapping("/findByUserNameOrEmail")
    public User findByUserNameOrEmail(@RequestParam(required = false) String userName, @RequestParam(required = false) String email) {
        return myUserService.findByUserNameOrEmail(userName, email);
    }

    @RequestMapping("/findAll")
    public List<User> findAll(@RequestParam int page, @RequestParam int pageSize) {
        return myUserService.findAll(page, pageSize);
    }

    @RequestMapping("/findAllInfo")
    public List<UserAndInfo> findAllInfo(@RequestParam int page, @RequestParam int pageSize) {
        return myUserService.findAllInfo(page, pageSize);
    }
}
