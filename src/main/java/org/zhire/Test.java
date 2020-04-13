package org.zhire;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Maps;
import org.Person;
import org.zhire.pojo.User;
import org.zhire.utils.Item;
import org.mindrot.jbcrypt.BCrypt;

//import javax.persistence.Persistence;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * @Author: chenqi
 * @Date: 2019.3.6 14:19
 */
public class Test {
    public static void main(String[] args) {
//        String logStr = new StringBuilder()
//                .append(" spreadWorkbookTagId:").append("aaa")
//                .append(" version:").append("bbb")
//                .toString();
//        System.out.println(logStr);
//
//       String sl = "0.09";
//        BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(sl));
//        System.out.println(bigDecimal);

//        Set s = new HashSet();
//        s.add(1);
//        s.add(2);
//        s.add(3);
//        String ss = "[";
//        for (Object o : s) {
//            ss += o.toString() + ",";
//        }
//        String s1 = ss.substring(0, ss.lastIndexOf(","))+"]";
//        System.out.println(ss);
//        System.out.println(s1);
//        long[] param = new long[10];
//        param[0]=1;
//        param[1]=2;
//        param[2]=3;
//
//        for (long s : param) {
//            System.out.println(s);
//        }
//     System.out.println(param);
//        JSONArray array = JSON.parseArray(s);
//        for (Object o : array) {
//
//
//        }
//
//        System.out.println(array);

//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(date));
//
//        int maxCurrentMonthDay = 0;
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("当前时间: " + sdf.format(calendar.getTime()));
//
//
//        Calendar calendar5 = Calendar.getInstance();
//        maxCurrentMonthDay = calendar5.getActualMaximum(Calendar.DAY_OF_MONTH);
//        calendar5.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
//        calendar5.set(Calendar.DAY_OF_MONTH, 1);
//        System.out.println("上月第一天: " + sdf.format(calendar5.getTime()));
//        System.out.println(getLastMonthFirstDay());
//        System.out.println(getLastMonthLastDay());
//        Calendar calendar6 = Calendar.getInstance();
//        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
//        calendar6.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
//        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
//        calendar6.set(Calendar.DAY_OF_MONTH, maxCurrentMonthDay);
//        System.out.println("上月最后一天: " + sdf.format(calendar6.getTime()));

//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.MONTH, 0);
//        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
//        System.err.println(c.getTime());//这个就是本月的第一天；
//        System.out.println(sdf.format(c.getTime()));
//
//        Calendar c1 = Calendar.getInstance();
//        c1.add(Calendar.MONTH, 0);
//        c1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
//        c1.add(Calendar.DATE, -1);//设置上个月的最后一天
//        System.err.println(c1.getTime());//这个就是上月的最后一天；  
//        System.out.println(sdf.format(c1.getTime()));
//        LocalDate now = LocalDate.now();
//        System.out.println(now.getDayOfMonth());
//        System.out.println(now.getMonthValue());
//        System.out.println(now.getYear());
//        System.out.println(LocalDate.now().toString());
        List<Person> list = new ArrayList();
//        list.add("aaa");
//        list.add("bbb");
//        list.add("ccc");
//        for (Object o : list) System.out.println(o);

        list.add(new Person(1, "zs", 11, new Date()));
        list.add(new Person(2, "ls", 12, new Date()));
        list.add(new Person(1, "ww", 13, new Date()));
        // Object[] objects = list.stream().toArray();
        // System.out.println(objects.length);

        list.stream().forEach(d -> {
            System.out.println(d);               // User{name='zs', age=11, birthday=Sun Apr 28 15:41:23 CST 2019} ...
            System.out.println(d.getName());     // zs ...
            System.out.println(d.getAge());      // 11 ...
            System.out.println(d.getBirthday()); // Sun Apr 28 15:41:23 CST 201 ...

        });

        // 使用Java8的方法引用
        // User{name='zs', age=11, birthday=Sun Apr 28 15:41:23 CST 2019}
        // User{name='ls', age=12, birthday=Sun Apr 28 15:41:23 CST 2019}
        // User{name='ww', age=13, birthday=Sun Apr 28 15:41:23 CST 2019}
        list.forEach(System.out::println);

        Map<Integer, Person> personMap = list.stream().collect(Collectors.toMap(Person::getId, a -> a, (k1, k2) -> k1));
        personMap.forEach((k, v) -> {
            System.out.println(k); // 1 ...
            System.out.println(v); // User{name='zs', age=11, birthday=Sun Apr 28 15:38:24 CST 2019} ...
        });

        // list.sort((o1, o2) ->   );
        System.out.println("===================");

        Map map = Maps.newConcurrentMap();
        map.put("name", "张三");
        map.put("age", 12);
        map.put("birthday", new Date());
        map.forEach((k, v) -> {
            System.out.println(k); // birthday ...
            System.out.println(v); // Sun Apr 28 15:41:23 CST 2019 ...
        });
        System.out.println("===================");
        List<String> asList = asList("a1", "a2", "a3");
        asList.stream().forEach(System.out::println);

    }

    public static String getLastMonthFirstDay() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(sdf.format(date));

        int maxCurrentMonthDay = 0;
        // Calendar calendar = Calendar.getInstance();
        // System.out.println("当前时间: " + sdf.format(calendar.getTime()));


        Calendar calendar5 = Calendar.getInstance();
        maxCurrentMonthDay = calendar5.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar5.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
        calendar5.set(Calendar.DAY_OF_MONTH, 1);
        String s = sdf.format(calendar5.getTime());
        // System.out.println("上月第一天: " + sdf.format(calendar5.getTime()));
        return s;

    }

    private static String getLastMonthLastDay() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(sdf.format(date));

        int maxCurrentMonthDay = 0;
        // Calendar calendar = Calendar.getInstance();
        Calendar calendar6 = Calendar.getInstance();
        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar6.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar6.set(Calendar.DAY_OF_MONTH, maxCurrentMonthDay);
        String s = sdf.format(calendar6.getTime());
        // System.out.println("上月最后一天: " + sdf.format(calendar6.getTime()));
        return s;
    }

    @org.junit.Test
    public void test() {

        // toList
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());

        // 转大写  map,将一个流中的值转换成一个新的流。 toUpperCase
        List<String> collect = Stream.of("a", "b", "hello").map(s -> s.toUpperCase()).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);

        // 找出字符串中以数字开头的字符串 filter
        List<String> list1 = Stream.of("aaa", "1bbb", "ccc").filter(value -> isDigit(value.charAt(0))).collect(Collectors.toList());
        list1.stream().forEach(System.out::println);

        // 找出年龄最小 min
        Person person = Stream.of(new Person("zs", 13, new Date()),
                new Person("zs", 11, new Date()))
                .min(Comparator.comparing(person1 -> person1.getAge())).get();
        System.out.println(person);

        // 求和 reduce
        Integer sum = Stream.of(1, 2, 3).reduce((num1, num2) -> num1 + num2).get();
        System.out.println(sum);

    }

    @org.junit.Test
    public void t() {
        List<Integer> integers = asList(1, 2, 3, 4);
        List<Integer> collect = integers.stream().collect(Collectors.toList());
        assertEquals(integers, collect);
        System.out.println("==================");
        Set<Integer> numbers = new HashSet<>(asList(4, 3, 2, 1));
        List<Integer> setList = numbers.stream().sorted().collect(Collectors.toList());
        assertEquals(asList(1, 2, 3, 4), setList);
    }


    @org.junit.Test
    public void t1() {
        List<String> list = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(toList());
        list.stream().forEach(System.out::println);
    }

    @org.junit.Test
    public void t2() {
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
        System.out.println(concat);
    }


    @org.junit.Test
    public void dxc() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Object> submit = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                int i = 0;
                for (; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "线程1 .....");
                    System.out.println(i);
                    Thread.sleep(1000);
                }
                return i;
            }
        });
        Future<Object> submit2 = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                int i = 5;
                for (; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "线程2 .....");
                    System.out.println(i);
                    Thread.sleep(1000);
                }
                return i;
            }
        });
        service.shutdown();

        Integer i1 = (Integer) submit.get();
        Integer i2 = (Integer) submit2.get();
        System.out.println(i1);
        System.out.println("===================================");
        System.out.println(i2);

    }

    @org.junit.Test
    public void dxc2() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.execute(new test3());
            System.out.println("************* " + i + " *************");
        }
        service.shutdown();
    }


    public class test3 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程被调用了。");
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @org.junit.Test
    public void dxc3lambda() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<Integer> submit = service.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "  One");
            return 1;
        });
        Future<Integer> submit1 = service.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "  Two");
            return 2;
        });
        System.out.println(submit.get());
        System.out.println(submit1.get());


        service.shutdown();


    }

    // ====================================================================== //
    @org.junit.Test
    public void test04() {
        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);
        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);
        // 十名选手
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            final int no = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 如果当前计数为零，则此方法立即返回。
                        // 等待
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000L));
                        System.out.println(" No." + no + " arrived");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }
                }
            };
            executorService.execute(run);
        }
        System.out.println("Game start...");
        // begin减一，开始游戏
        begin.countDown();
        try {
            end.await();
            // 等待end变为0，即所有选手到达终点
            System.out.println("Game over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


    @org.junit.Test
    public void test001() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; ++i) // create and start threads
        {
            System.out.println("start " + i);
            new Thread(new Worker(startSignal, doneSignal)).start();
            System.out.println("end   " + i);
        }

        System.out.println("hello");  // don't let run yet

        System.out.println("=========== start begin ======== " + startSignal.getCount());
        startSignal.countDown();      // let all threads proceed
        // 每调用一次这个方法，在构造函数中初始化的count值就减1。
        // 所以当N个线程都调 用了这个方法，count的值等于0，
        // 然后主线程就能通过await()方法，恢复执行自己的任务。
        System.out.println("============ start end   ======= " + startSignal.getCount());

        System.out.println("=========== done begin ======== " + doneSignal.getCount());
        doneSignal.await();           // wait for all to finish
        // 调用此方法会一直阻塞当前线程，直到计时器的值为0，除非线程被中断。
        System.out.println("=========== done end   ======= " + doneSignal.getCount());

    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();

                doWork();

                doneSignal.countDown();
            } catch (Exception ex) {
            } // return;
        }

        void doWork() {
            System.out.println(Thread.currentThread().getName() + " worker ");
        }
    }

    @org.junit.Test
    public void testJson() {
        String arr = "{depts:}";

    }

    @org.junit.Test
    public void testu() {
        List<User> list = new ArrayList();
        list.add(new User("zs", "111"));
        list.add(new User("ww", "222"));
        list.add(new User("zl", "333"));

        for (User user : list) {
            user.setPassword("zs");
        }
        list.stream().forEach(l -> {
            System.out.println(l);
        });
    }

    @org.junit.Test
    public void xcc() throws InterruptedException, ExecutionException {
        // 开始时间
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 4000; i++) {
            list.add(i + "");
        }
        // 每500条数据开启一条线程
        int threadSize = 500;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadSize == 0;

        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        List<String> cutList = null;

        // 确定每条线程的数据
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(threadSize * i, dataSize);
            } else {
                cutList = list.subList(threadSize * i, threadSize * (i + 1));
            }
            // System.out.println("第" + (i + 1) + "组：" + cutList.toString());
            final List<String> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    // sms begin
                    System.out.println(Thread.currentThread().getName() + "线程：" + listStr);
                    // sms end
                    return 1;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }

        List<Future<Integer>> results = exec.invokeAll(tasks);
        // 关闭线程池
        exec.shutdown();
        System.out.println("线程任务执行结束");
        System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
    }

    @org.junit.Test
    public void wqerwe() {
        boolean is = false;
        if (!is) {
            System.out.println("111111");
        }
    }


    @org.junit.Test
    public void testu333() {
        Map map = new HashMap();
        map.put("name", "chenqi");
    }

    @org.junit.Test
    public void testu3eee33() throws ParseException {
//        User user = new User();
//        User user1 = new User();
//        System.out.println(user == user1);
//        System.out.println(user.equals(user1));
//        System.out.println(user.hashCode());
//        System.out.println(user1.hashCode());
//        System.out.println(user.hashCode() == user1.hashCode());

//        int a = 1 << 1;
//        int b = 1 << 2;
//        int c = 1 << 3;
//        int d = 1 << 4;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        String createTime = "2019-07-09T06:41:08.000+0000";
        Date into = inputFormat.parse(createTime);
        String format = df.format(into);
        System.out.println(format);
    }

    @org.junit.Test
    public void test11111() {
        //  double a = 2.6 + 3.2 + 2.8 + 2.5 + 2.6 + 2.9 + 3 + 3.7 + 2.5 + 6 + 11.2 + 4.6 + 3.1 + 2.9 + 3.2 + 3.7 + 3.8 + 3.5 + 3.8;
        // System.out.println(a);
        Set<String> set = new HashSet();
        set.add("");
        set.add("112");
        set.add("13312");
        set.add("");
        for (String o : set) {
            System.out.println(o);
            try {
                if (StringUtils.isEmpty(o)) {
                    System.out.println(1111);
                }
                int as = 1 / 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("1111111111111111111111");
    }


    @org.junit.Test
    public void duan() {
        String longUrl = "要转换的长链接";
        // 测试生成1千万个短链接 看是否有重复
//        Set set = new HashSet();
//        for (int i = 0; i < 10000000; i++) {
//            StringBuilder builder = dotest();
//            set.add(builder);
//        }
//        System.out.println(set.size());
        StringBuilder shortUrl = dotest();
        // if... 查库里面是否存在这个短链接 如果存在重新生成一个
        // else... 返回页面
        // return "https://qi.rs/" + shortUrl;
    }

    StringBuilder dotest() {
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder shortUrl = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int a = random.nextInt(chars.length);
            shortUrl = sb.append(chars[a]);
        }
        System.out.println(shortUrl);
        return shortUrl;

    }

    @org.junit.Test
    public void dddddd() {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(i);
//        }

        try {
            int a = 1 / 2;
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("哈哈哈");
        }

    }

    @org.junit.Test
    public void generateWord() {
        String[] beforeShuffle = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        for (int i = 0; i < 1000; i++) {
            List list = Arrays.asList(beforeShuffle);
            Collections.shuffle(list);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < list.size(); j++) {
                sb.append(list.get(j));
            }
            String afterShuffle = sb.toString();
            String result = afterShuffle.substring(5, 10);
            System.out.println(result);
        }

    }


    @org.junit.Test
    public void generateWordwww() throws IOException {
        // 150
        BufferedReader br = new BufferedReader(new FileReader("D:\\work\\ideawork\\myBlog\\src\\main\\resources\\xxx.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("yyy.txt"));
        for (int i = 20000; i < 21000; i++) {
            bw.write(br.readLine());
            bw.write("\n");
        }
        bw.close();
    }

    @org.junit.Test
    public void qw4eqweq() throws IOException {
        String s = "{\"name\":\"\\x\"}";

        Object parse = JSON.parse(s);
        System.out.println(parse);

    }

    @org.junit.Test
    public void testMap() {
        String s = "{\n" +
                "  \"serialNum\": \"4b98790e-86ef-4864-92d4-2667b305d00d\",\n" +
                "  \"port\": 10000,\n" +
                "  \"appsecret\": \"PTPYJ9SlN0tvrm7jr0Un\",\n" +
                "  \"phones\": [\n" +
                "    \"17601026576\"\n" +
                "  ],\n" +
                "  \"prodIpAddress\": \"10.1.2.177\",\n" +
                "  \"appkey\": \"igicv1X4LQ6iZdqzpRwA\",\n" +
                "  \"templateCode\": \"2322\",\n" +
                "  \"params\": [\n" +
                "    \"8414\",\n" +
                "    \"17601026576\"\n" +
                "  ],\n" +
                "  \"url\": \"http://newyfb.5ifapiao.com/api4.0/v4/smsSend\"\n" +
                "}";
        JSONObject smsSend = (JSONObject) JSONObject.parse(s);
        String key = "";
        if (null == smsSend.get("phones")) {
            JSONArray array = (JSONArray) smsSend.get("to");
            String mail = array.get(0).toString();
            key = mail + "_" + System.currentTimeMillis();
            System.out.println("1111");
        }
        if (null == smsSend.get("to")) {
            JSONArray array = (JSONArray) smsSend.get("phones");
            String phone = array.get(0).toString();
            key = phone + "_" + System.currentTimeMillis();
            System.out.println("2222");

        }
        System.out.println(key);
    }

    boolean a;


    @org.junit.Test
    public void tt() {
        int MAX_VALUE = 0x7fffffff;
        System.out.println(MAX_VALUE);

        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal(23)),
                new Item("apple", 20, new BigDecimal(32)),
                new Item("orange", 30, new BigDecimal(13)),
                new Item("orange", 20, new BigDecimal(33)),
                new Item("orange", 10, new BigDecimal(63)),
                new Item("orange", 50, new BigDecimal(41)),
                new Item("peach", 20, new BigDecimal(26)),
                new Item("peach", 30, new BigDecimal(42)),
                new Item("peach", 40, new BigDecimal(24)),
                new Item("peach", 10, new BigDecimal(12))
        );

        Map<String, List<Item>> collect = items.stream().collect(Collectors.groupingBy(Item::getName));
        System.out.println(JSON.toJSONString(collect));


        // 分组，计数
        Map<String, Long> counting = items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
        System.out.println(counting);

        // 分组，计数，数量
        Map<String, Integer> sum = items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println(sum);


    }

    @org.junit.Test
    public void t3333t() {
// 22044287.85
        double[] ddd = {683, 135, 0.01, 0.01, 0.01, 0.01, 0.06, 0.01, 683, 0.01, 0.03, 683, 2000000,
                683, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 683, 0.01, 0.01, 683, 0.01, 90, 683, 0.01,
                683, 0.01, 0.01, 0.01, 0.01, 683, 683, 0.01, 683, 0.01, 0.01, 683, 683, 90, 0.01, 0.01, 0.01,
                683, 90, 0.01, 683, 0.01, 0.01, 0.01, 123, 0.01, 0.01, 90, 0.01, 30, 30, 683, 0.01, 683, 683,
                0.01, 90, 30, 683, 0.01, 0.01, 30, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 683, 0.01, 683,
                0.01, 683, 0.01, 0.03, 0.01, 0.01, 0.01, 683, 0.01, 0.01, 0.01, 20001, 0.01, 20000000,
                0.01, 30, 135, 0.01, 5535.11, 0.01, 0.01, 0.01, 683, 683, 0.01, 0.01, 683};
        List<Double> doubles = new ArrayList<>();
        for (double v : ddd) {
            doubles.add(v);
        }

        List<String> strings = new ArrayList<>();
//        doubles.add(0.01);
//        doubles.add(0.09);
//        doubles.add(0.13);
//        doubles.add(1.91);
//        doubles.add(99.01);
//        doubles.add(100.09);

        for (Double aDouble : doubles) {
            strings.add(String.valueOf(aDouble));
        }

        BigDecimal bigDecimal = new BigDecimal("0.00");
        for (String s : strings) {
            bigDecimal = bigDecimal.add(new BigDecimal(s));
        }
        System.out.println(Double.valueOf(bigDecimal.toString()));


    }

    @org.junit.Test
    public void ttttqw3e2() {
        Map map = new HashMap(8);
        map.put("rtyuu", 42341);
        map.put("SDFSD", 12312);
        map.put("AS", 1453);
        map.put("ghdfh", 1645);
        map.put("asdf", 17456);
        map.put("asdfsa", 14356);
        map.put("vzxcvzx", 11234);
        map.put("piopyu", 1123);
        for (Object o : map.keySet()) {
            System.out.println(o + " : " + o.hashCode());
        }
        System.out.println();
        map.put("ertyurety", 11234);
        map.put("qweqe", 1756);
        map.put("qweqwe", 1756);
        for (Object o : map.keySet()) {
            System.out.println(o + " : " + o.hashCode());
        }
    }

    // BCryp加密
    @org.junit.Test
    public void testpass() {
        String password = "123456";
        String afterPass = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(afterPass);
        boolean checkpw = BCrypt.checkpw(password, afterPass);
        System.out.println(checkpw);
//        Map urlMap = Maps.newConcurrentMap();
//        urlMap.put(String.valueOf(1), "http://test.5ifapiao.com:8888/api-gateway/aosp-file-service/aosp-file-service/file/download/singlefile?id=44217235bbde44a68548152648cdb111");
//        urlMap.put(String.valueOf(2), "fail");
//        ExpertCommonRspVo success = ExpertCommonRspVo.success(urlMap);
//        System.out.println(JSON.toJSONString(success));
    }


}