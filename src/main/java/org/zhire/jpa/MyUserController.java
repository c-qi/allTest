package org.zhire.jpa;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Api(value = "jpa-API", description = "jpa") swagger的访问 // http://127.0.0.1:7971/swagger-ui.html#/
@Api(name = "jpa-AP", description = "jpa")
// jsondoc的访问 http://localhost:7971/jsondoc-ui.html# 输入 http://localhost:7971/jsondoc
@RestController
@RequestMapping("/userTest")
public class MyUserController {
    @Autowired
    private MyUserService myUserService;

    @RequestMapping("/insert")
    public User insert(@RequestBody User user) {
        return myUserService.insert(user);
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

    @RequestMapping("/findFirst")
    public ZpUserBusiness findFirst(@RequestParam ZpUserBusiness.FROMTYPE fromType) {
//        ZpUserBusiness.FROMTYPE test = null;
//        if (fromType == 0){
//            test = ZpUserBusiness.FROMTYPE.TEST;
//        }
//        if (fromType == 1){
//            test = ZpUserBusiness.FROMTYPE.WORKS;
//        }
        return myUserService.findFirst(fromType);
    }

    @RequestMapping("/findAllList")
    public void findAll() {
        myUserService.findAllList();
    }

    @RequestMapping("/update")
    public void update(String id) {
        myUserService.updateById(id);
    }

    @RequestMapping("/findIn")
    public void findIn() {
        myUserService.findIn();
    }
}
