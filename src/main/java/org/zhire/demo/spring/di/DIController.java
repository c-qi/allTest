package org.zhire.demo.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/di")
public class DIController {

    @Autowired
    // @Qualifier("DICat")
    private DIAnimal diAnimal;

    @RequestMapping("/t")
    public void test() {
        diAnimal.use();
    }
}
