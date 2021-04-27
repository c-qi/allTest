package org.zhire.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date 2021/4/14 11:47
 * @Author by chenqi
 */
//@Component
public class IfTest {
    //    @PostConstruct
    public void init() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(300);
        executorService.execute(() -> {
            System.out.println("2e");
        });
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10000; i++) {
//            new Thread(() -> {
//                String s = HttpRequest.get("http://ums-test-api.meixiu.mobi/api/works/s/v1/works/getWorksEsById?worksId=88989")
//                        .execute()
//                        .body();
//                System.out.println(s);
//            }).start();
//        }
//
//    }

}
