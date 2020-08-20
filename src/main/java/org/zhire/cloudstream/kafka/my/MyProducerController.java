package org.zhire.cloudstream.kafka.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/kafkamy")
public class MyProducerController {
    @Autowired
    private MySendService sendService;

    @RequestMapping("/send")
    public String send(@RequestParam("msg") String msg){
        sendService.sendMsg(msg);
        return "ok";
    }

}
