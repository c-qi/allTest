package org.zhire.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenqi
 * @Date: 2020.7.30 09:13
 */
@RestController
@RequestMapping("/spring")
public class MyController {

    @Autowired
    private List<ObjectB> objectBS;

    @RequestMapping("/test")
    private void test() {
        for (int i = 0; i < objectBS.size(); i++) {
            System.out.println(objectBS.get(i));
        }
        System.out.println("----");

    }
}
