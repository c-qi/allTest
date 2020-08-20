package org.zhire.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.zhire.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @RequestMapping("/article")
    public String login(HttpServletResponse response) throws Exception {
        return null;
    }

    @RequestMapping("/test")
    public String login(@RequestParam(required = false)Long id, @RequestParam String name) throws Exception {
        System.out.println(id);
        System.out.println(name);
        return "ok";
    }

}
