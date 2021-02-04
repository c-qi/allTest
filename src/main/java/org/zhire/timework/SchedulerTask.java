package org.zhire.timework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhire.controller.TestController;

/**
 * @Author: chenqi
 * @Date: 2019.3.28 14:45
 */
//@Component
//@RequestMapping("/doJob")
public class SchedulerTask {

    @Autowired
    private TestController testController;
//                  秒 分 时 日 月 星期
//  @Scheduled(cron ="0 30 0 1 * *") // 每个月1号 0:30 执行
//  @Scheduled(cron ="0 30 0 1 * ?") // 每个月1号 0:30 执行
//    @Scheduled(cron ="0/100 * * * * *") // 五秒执行一次
    @RequestMapping("/start")
    private void testWork(){
//        System.out.println(new Date());
//        testController.testwork();
        System.out.println("1231231");
 //       Timer timer =
    }


}
