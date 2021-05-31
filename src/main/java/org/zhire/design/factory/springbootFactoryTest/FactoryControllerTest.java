package org.zhire.design.factory.springbootFactoryTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Date 2021/5/31 16:50
 * @Author by chenqi
 */
@RestController
@RequestMapping("/factory")
public class FactoryControllerTest {

    @Autowired
    private HandelFactory handelFactory;

    @RequestMapping("/t")
    private Map s(@RequestParam String s1, @RequestParam String s2) {
        return handelFactory.setMap(s1, s2);
    }

    @RequestMapping("/t2")
    public String testSelect(@RequestParam Integer s1) {
        String handel = HandelFactory.getHandel(s1).handel();
        System.out.println();
        return handel;
    }

}
