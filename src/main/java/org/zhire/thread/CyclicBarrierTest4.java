package org.zhire.thread;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 用一个Excel保存了用户所有银行流水，每个Sheet保存一个账户近一年的每笔银行流水，
 * 现在需要统计用户的日均银行流水，先用多线程处理每个sheet里的银行流水，
 * 都执行完之后，得到每个sheet的日均银行流水，
 * 最后，再用barrierAction用这些线程的计算结果
 *
 * @Author: chenqi
 * @Date: 2020.9.7
 */
public class CyclicBarrierTest4 {

    public static void main(String[] args) {
        /*** 创建4个屏障，处理完之后执行当前类的run方法 */
        CyclicBarrier c = new CyclicBarrier(5);
        /*** 假设只有4个sheet，所以只启动4个线程 */
        Executor executor = Executors.newFixedThreadPool(4);
        /*** 保存每个sheet计算出的银流结果 */
        ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() { // 计算当前sheet的数据
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    // 计算完成，插入一个屏障
                    {
                        try {
                            c.await();
                            System.out.println(Thread.currentThread().getName() + " " + sheetBankWaterCount.get(Thread.currentThread().getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        try {
            c.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 汇总每个sheet计算出的结果
        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }// 将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println("结果：" + result);


    }

}