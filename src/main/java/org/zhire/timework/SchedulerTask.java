package org.zhire.timework;

import org.zhire.controller.TestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: chenqi
 * @Date: 2019.3.28 14:45
 */
@Component
public class SchedulerTask {

    @Autowired
    private TestController testController;
//                  秒 分 时 日 月 星期
//  @Scheduled(cron ="0 30 0 1 * *") // 每个月1号 0:30 执行
//  @Scheduled(cron ="0 30 0 1 * ?") // 每个月1号 0:30 执行
    @Scheduled(cron ="0/5 * * * * *") // 五秒执行一次
    private void testWork(){
//        System.out.println(new Date());
        testController.testwork();
 //       Timer timer =
    }


}
