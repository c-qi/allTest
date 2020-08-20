package org.zhire.cloudstream.kafka.defaultkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/kafka")
public class ProducerController {
    @Autowired
    private SendService sendService;

    @RequestMapping("/send")
    public String send(@RequestParam("msg") String msg){
        sendService.sendMsg(msg);
        return "ok";
    }

}
