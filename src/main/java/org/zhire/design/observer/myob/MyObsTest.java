package org.zhire.design.observer.myob;

/**
 * CompletableFuture<Object> future = CompletableFuture.supplyAsync();
 * future.get();
 * 上面调用future.get();会引起主线程阻塞，
 * 基于回调模式实现异步调用完成通知
 * 而主线程不会阻塞
 */
public class MyObsTest {

    public static void main(String[] args) {
        HandleDo handleDo = new HandleDo();
        handleDo.setAa(10);
        handleDo.get(a -> {
            System.out.println(a);
            return a;
        });

        System.out.println("Main");
    }
}
