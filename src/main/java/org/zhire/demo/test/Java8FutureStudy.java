package org.zhire.demo.test;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.Data;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.*;
import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * CompletableFuture使用总结
 * supplyAsync() 异步执行
 * .thenApply() 把上个输出流变成输入流进入
 * .thenAccept() 拿到输出结果
 * CompletableFuture.allOf().join(); 线程走到这里会暂停直到取到所有的future结果
 */
public class Java8FutureStudy {

    @Test
    public void testFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                // dosomething() 以异步方式在新的线程中执行耗时业务
                return null;
            }
        });

        // doOtherthing(); 异步操作的同时可以干其他的事
        try {
            // 获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
            // 如果不加时间会一直阻塞等待结果
            Object o = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程在等待过程中被中断
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 处理异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            // 在Future对象完成之前超过已过期
        }


    }

    @Data
    public class Shop {

        private String name;
        private String product;

        public Shop() {
        }

        public Shop(String name) {
            this.name = name;
        }

        private double calculatePrice(String product) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 9.00;
        }

        // 同步获取价格
        public double getPrice(String product) {
            return calculatePrice(product);
        }

        // 异步获取价格
        public Future<Double> getPriceAsync(String product) {
            CompletableFuture<Double> future = new CompletableFuture<>();
            new Thread(() -> {
                try {
                    double price = calculatePrice(product);
                    future.complete(price); // 如果价格正常返回，完成future操作，并设置价格
                } catch (Exception e) {
                    e.printStackTrace();
                    future.completeExceptionally(e); // 否则就抛出失败异常，完成本次future操作
                }
            }).start();
            return future;
        }

        // todo 异步获取价格优化代码
        public Future<Double> getPriceAsyncBetter(String product) {
            return supplyAsync(() ->
                    calculatePrice(product));
        }

        // 同步调用方法----遍历四个商店获取传入商品的价格
        public List<Double> findPrices(String product) {
            List<Double> collect = shops.stream().map(shop -> shop.getPrice(product)).collect(Collectors.toList());
            return collect;
        }

        // 使用并行流调用方法----遍历四个商店获取传入商品的价格
        public List<Double> findPricesBetrer(String product) {
            List<Double> collect = shops.parallelStream().map(shop -> shop.getPrice(product)).collect(Collectors.toList());
            return collect;
        }

        // 使用 CompletableFuture 发起异步请求
        public List<Double> findPricesBetrerByCompFuture(String product) {
            // 使用CompletableFuture以异步方式计算每种商品的价格
            List<CompletableFuture<Double>> futureList = shops.stream().map(
                    shop ->
                            supplyAsync(() -> shop.getPrice(product))).collect(Collectors.toList());
            // 等待所有异步操作结束
            List<Double> list = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
            return list;
        }

        //  流水线简单操作 先获取价格 然后价格加1 然后减2
        public List<Double> findPricesMap(String product) {
            List<Double> list = shops.stream()
                    .map(shop -> shop.getPrice(product))
                    .map(Add::priceAdd)
                    .map(Del::priceDel)
                    .collect(Collectors.toList());
            return list;
        }

        // 使用supplyAsync thenApply thenCompose 完成上面的流水线操作
        // thenCompose方法允许你对两个异步操作进行流水线，第一个操作完成时，将其结果作为参数传递给第二个操作
        public List<Double> findPricesBetterByBest(String product) {
            List<Double> list = new ArrayList<>();
            List<CompletableFuture<Double>> futureList = shops.stream()
                    .map(shop -> supplyAsync(
                            // 异步方式获取产品价格
                            () -> shop.getPrice(product)))
                    // 对上面的返回值加1
                    .map(future -> future.thenApply((Add::priceAdd)))
                    // 使用另外的异步执行价格减操作
                    .map(future -> future.thenCompose(
                            add -> supplyAsync(
                                    () -> Del.priceDel(add))))
                    .collect(Collectors.toList());
            List<Double> doubles = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
            return doubles;
        }

        // 流水线原始操作 这里不返回最后计算出来的值
        public Stream<CompletableFuture<Double>> findPricesBetterByBest2(String product) {
            Stream<CompletableFuture<Double>> futureStream = shops.stream()
                    .map(shop -> supplyAsync(
                            // 异步方式获取产品价格
                            () -> shop.getPrice(product)))
                    // 对上面的返回值加1
                    .map(future -> future.thenApply((Add::priceAdd)))
                    // 使用另外的异步执行价格减操作
                    .map(future -> future.thenCompose(
                            add -> supplyAsync(
                                    () -> Del.priceDel(add))));
            return futureStream;
        }

        // 合并两个独立的CompletableFuture对象
//        public List<Double> two(String product) {
//            List<Double> list = new ArrayList<>();
//            Shop shop = new Shop();
//
//            CompletableFuture.supplyAsync(() -> getPrice(product))
//                    .thenCombine(
//                            CompletableFuture.supplyAsync(
//                                    () -> shop.getRate(1.0, 1.0)),
//                            (price, rate) -> price * rate);
//            return null;
//        }


    }

    @Data
    static class Add {

        public static Double priceAdd(double price) {
            return price + 1;
        }
    }

    static class Del {
        public static Double priceDel(double price) {
            return price - 2;
        }


    }

    @Test
    public void CompletableFutureTest() throws Exception {
        Shop shop = new Shop();
        StopWatch watch = new StopWatch();
        watch.start();
        Future<Double> future = shop.getPriceAsync("apple"); // 异步查询价格
        // doOtherSomething() 执行其他任务
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
        }
        try {
            Double aDouble = future.get(); // 获取价格 如果未知会阻塞线程
            System.out.println("apple：" + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Future<Double> banana = shop.getPriceAsyncBetter("banana"); // 使用优化的CompletableFuture（）
        try {
            Double aDouble = banana.get();
            System.out.println("banana:" + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
    }

    private final List<Shop> shops = Arrays.asList(new Shop("tmall"),
            new Shop("vip"),
            new Shop("jd"),
            new Shop("pdd"));

    /**
     * 同步和异步调用方法
     */
    @Test
    public void getMyProductSync() {
        // 同步调用
        Shop shop = new Shop();
        StopWatch watch = new StopWatch();
        watch.start();
        List<Double> apple = shop.findPrices("myPhone27S");
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
        System.out.println(apple);

        //  并行调用
        StopWatch watch2 = new StopWatch();
        watch2.start();
        List<Double> apple2 = shop.findPricesBetrer("myPhone27S");
        watch2.stop();
        System.out.println(watch2.getTotalTimeMillis());
        System.out.println(apple2);


        // watch3异步调用
        StopWatch watch3 = new StopWatch();
        watch3.start();
        List<Double> apple3 = shop.findPricesBetrerByCompFuture("myPhone27S");
        watch3.stop();
        System.out.println(watch3.getTotalTimeMillis());
        System.out.println(apple3);

    }

    @Test
    public void getBest() {
        Shop shop = new Shop();
        StopWatch watch = new StopWatch();
        watch.start();
        List<Double> apple = shop.findPricesBetterByBest("apple");
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
        System.out.println(apple);
    }

    // thenAccept可以拿到返回的值 然后可以进行其他的操作
    @Test
    public void getBest2() {
        Shop shop = new Shop();
        ArrayList<Object> list = new ArrayList<>();
        StopWatch watch = new StopWatch();
        watch.start();
        CompletableFuture[] array = shop.findPricesBetterByBest2("apple").
                map(f -> f.thenAccept(p -> list.add(p)))
                .toArray(size -> new CompletableFuture[size]);
        System.out.println("-------------");
        allOf(array).join();
        System.out.println("-------------");
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
        System.out.println(list);
    }

    @Test
    public void t() throws Exception {
        CompletableFuture<Integer> future = supplyAsync(() -> get(1));
        CompletableFuture<String> future1 = supplyAsync(() -> get("s"));
        //CompletableFuture.allOf(future,future1).join();
        System.out.println(future.get());
        System.out.println(future1.get());

    }

    public Integer get(int i) {
        i *= i;
        System.out.println(Thread.currentThread().getName() + " " + i + " " + "ok");
        try {
            Thread.sleep(1000 * 60 * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    public String get(String s) {
        s += s;
        return s;
    }

    @Test
    public void tt() throws Exception {
        Set<Long> set = Sets.newHashSet();
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i + "");
        }
        list.parallelStream().forEach(l -> {
            set.add(Thread.currentThread().getId());
            System.out.println(Thread.currentThread().getName() + " " + l);
        });
        System.out.println("--------" + JSON.toJSONString(set));
        Thread.sleep(3000);
        // 异步操作 不需要返回值
        CompletableFuture<Void> f5 = runAsync(() -> {
            list.parallelStream().forEach(l -> {
                System.out.println(Thread.currentThread().getName() + " " + l);
            });
        });
        CompletableFuture<Void> f1 = runAsync(() -> get(1));
        CompletableFuture<Void> f2 = runAsync(() -> get(2));
        CompletableFuture<Void> f3 = runAsync(() -> get(3));
        CompletableFuture<Void> f4 = runAsync(() -> get(4));
        // 异步操作 需要返回值
//        CompletableFuture<Integer> f11 = supplyAsync(() -> get(1));
        CompletableFuture<Integer> f6 = supplyAsync(() -> get(5));
        CompletableFuture<Integer> f7 = supplyAsync(() -> get(6));
        CompletableFuture<Integer> f8 = supplyAsync(() -> get(7));
        CompletableFuture<Integer> f9 = supplyAsync(() -> get(8));
        CompletableFuture<Integer> f10 = supplyAsync(() -> get(9));


        allOf(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10).join();
//        allOf(f1, f2, f3, f4, f5).join();

    }

    @Test
    public void t2() throws Exception {
        CompletableFuture<Integer> f1 =
                supplyAsync(() -> {
                    try {
                        System.out.println(0);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 0;
                });
        CompletableFuture<Integer> f2 = supplyAsync(() -> 0)
                .thenApply(i -> i + 3)
                .whenComplete((t, u) -> {
                    System.out.println(t);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).handle((j, k) -> j * j);
        allOf(f1, f2).join();
        System.out.println(f2.get());
        System.out.println(f1.get());
    }

    @Test
    public void tere() {
        CompletableFuture<Integer> f1 = supplyAsync(() -> this.add(1));
        CompletableFuture<Integer> f2 = supplyAsync(() -> this.add(2));
        allOf(f1, f2).join();
        try {
            System.out.println(f1.get(3, TimeUnit.SECONDS));
            System.out.println(f2.get(10, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Future<Integer> integerFuture = ThreadUtil.execAsync(() -> add(1));
        try {
            System.out.println(integerFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer add(int i) {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i += i;
        return i;
    }
}
