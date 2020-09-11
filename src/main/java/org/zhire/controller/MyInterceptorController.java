package org.zhire.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class MyInterceptorController {

    @GetMapping("/test")
    public String exportPdf() {
        return "OK";
    }

}
