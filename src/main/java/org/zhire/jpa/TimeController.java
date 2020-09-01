package org.zhire.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("time")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @RequestMapping("/test")
    public String test(){
        timeService.save();
        return "ok";
    }
}
