package org.zhire.demo.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhire.demo.spring.ioc.IOCUser;

@RestController
@RequestMapping("/aop")
public class AOPController {

    @Autowired
    private AOPService aopService;

    @RequestMapping("/r")
    public void printUser(@RequestParam String  name) {
        IOCUser user = new IOCUser();
        user.setName(name);
        aopService.printUser(user);
    }
}
