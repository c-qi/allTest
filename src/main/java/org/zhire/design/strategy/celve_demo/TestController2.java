package org.zhire.design.strategy.celve_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenqi
 * @Date: 2019.7.25 16:39
 */
@RestController
@RequestMapping("/test")
public class TestController2 {
    @Autowired
    private SimpleContext simpleContext;

    @GetMapping("/choose")
    public String choose(@RequestParam String poolId){
        return simpleContext.getResource(poolId);
    }
}
