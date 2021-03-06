package org.zhire.demo.test;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.Person;
import org.junit.Test;
import org.zhire.config.MyException;
import org.zhire.config.MyException2;
import org.zhire.model.UserDTO;
import org.zhire.pojo.User;
import org.zhire.utils.Item;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * @Author: chenqi
 * @Date: 2020.4.21 09:15
 */
public class JavaStreamTest {
    @org.junit.Test
    public void listsss() {
        // List<String> list = settleBills.stream().map(SettleBill::getBillId).collect(Collectors.toList());

//        List<BillInfo> billInfoList = new ArrayList<>();

//        for (String s : list) {
//            BillInfo billInfo = billInfoClient.getBillById(s);
//            billInfoList.add(billInfo);
//        }

//        List<BillInfo> billInfoList =
//                list.stream().map(l -> billInfoClient.getBillById(l)).collect(Collectors.toList());
        //            for (ConsultExpertShare consultExpertShare : expertShareDistributorList) {
//                String expertShareDistributorId = consultExpertShare.getDistributorId();
//                // 获取工作时间，判断是否在工作时间内
//                ExpertCommonRspVo<Map<String, Object>> rspVo = consultEquityService.getWorkTime(expertShareDistributorId);
//                if("0000".equals(rspVo.getCode()) && !(boolean) rspVo.getData().get("isWorkTime")) {
//                    // 非工作时间 不分配未咨询订单
//                   continue;
//                }else{
//                    consultQuestion.setDistributorId(consultExpertShare.getDistributorId());
//                    List<ConsultQuestion> shareQuestList = consultExpertClient.getQuestionByExpertId(consultQuestion);
//                    if (shareQuestList != null && !shareQuestList.isEmpty()) {
//                       shareQuestList.forEach(questions -> questionList.add(questions));
//                    }
//                }
//            }

    }

    @Test
    public void test01() {
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

    /**
     * java8 过滤
     */
    @Test
    public void filter() {
        List<User> list = new ArrayList<>();
        //List<User> expertList =
        //  list.stream().filter(l -> !l.getName().equals("cq")).collect(Collectors.toList());
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

        // 倒叙排列
        List<Item> list = items.stream().sorted(Comparator.comparing(Item::getQty).reversed()).collect(toList());
        System.out.println(list);

        int sums = items.stream().mapToInt(Item::getQty).sum();
        System.out.println(sums);


        // 按字段分组
//        List<OutBoundYiZhiRecord> recordList = new ArrayList<>();
//        Map<String, List<Long>> listMap = recordList.stream()
//                .collect(Collectors.groupingBy(OutBoundYiZhiRecord::getMobile,
//                        Collectors.mapping(OutBoundYiZhiRecord::getJobId, Collectors.toList())));
//        log.info("map:{}", JSON.toJSONString(listMap));

    }

    /**
     * filter过滤
     */
    @Test
    public void testFiltef() {
        //创建一个字符串列表，每个字符串长度大于2
        List<String> costBeforeTax = Arrays.asList("abc", "bcd", "defg", "jk");
        List<String> filtered = costBeforeTax.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", costBeforeTax, filtered);
    }

    /**
     * distinct去重
     */
    @Test
    public void distinct() {
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s, Square Without duplicates : %s %n", numbers, distinct);
    }

    /**
     * 对象去重
     */

    @Test
    public void trt3() {
        List<UserDTO> list = new ArrayList<>();
        UserDTO dto = new UserDTO().setName("cq").setPassword("q3wq").setId(1L);
        UserDTO dto1 = new UserDTO().setName("cq").setPassword("q3wq").setId(1L);
        UserDTO dto2 = new UserDTO().setName("cq").setPassword("q3wq").setId(1L);
        list.add(dto);
        list.add(dto1);
        list.add(dto2);
        list = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing(UserDTO::getName))), ArrayList::new)
        );
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 字符串分割转list
     * list转字符串
     */
    @Test
    public void tests() {
        String s = "chenqi,qiqi,qi";
        List<String> list = Arrays.stream(s.split(",")).collect(Collectors.toList());
        List<String> lists = Arrays.asList("cq", "ccqq", "cxq");
        String s1 = String.join(",", lists);
        System.out.println(list);
        System.out.println(s1);

    }

    @Test
    public void t3() {
        List<String> list = Stream.of(new String[]{"Hello", "World"}).flatMap(str -> Arrays.stream(str.split(""))).distinct()
                .collect(Collectors.toList());
        System.out.println(list);
        List<Stream<String>> collect = Stream.of(new String[]{"Hello", "World"}).map(str -> Arrays.stream(str.split(""))).distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void ttt() {
//        Integer isDefault = Optional.ofNullable(queryByUser(userId))
//                .filter(List::isEmpty)
//                .map(list -> 1)
//                .orElse(0);

//        Optional.ofNullable(request.getMobile())
//                .map(mobile -> userServiceV2.getUserByMobile(mobile, subject))
//                .map(Response::getPayload)
//                .map(User::getId)
//                .ifPresent(request::setUid);

//        List<Integer> list = CollUtil.newArrayList(1, 2, 3);
        List<Integer> list = null;
        Optional.ofNullable(list)
                .map(this::setId)
                .ifPresent(l -> l.forEach(System.out::println));
        User user = null;
        Optional<Object> o = Optional.of(user);
        if (o.isPresent()) {
            System.out.println(9999);
        }
    }

    private List<Integer> setId(List<Integer> ids) {
        List<Integer> list = new ArrayList<>();
        ids.forEach(l -> {
            list.add(l + 1);
        });
        return list;

    }

    /**
     * map对原来的集合操作后返回一个新的集合。此集合和原集合无任何关系，且不会改变原集合的任何东西
     * foreach操作的是原集合，返回的还是原集合
     */
    @Test
    public void t33() {
        User user = new User();
        user.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        ArrayList<User> list = CollUtil.newArrayList();
        list.add(user);
        list.add(user2);
        List<User> users = list.stream().map(this::getUser).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(users));
        Optional.of(user).ifPresent(u -> {
            try {
                ger(u);
            } catch (MyException2 myException2) {
                System.out.println("-------");
                myException2.printStackTrace();
                lambdaThrowException(myException2);
            }
        });

    }

    /**
     * 把受检异常包装成非受检异常再抛出，避免破坏Java异常的结构，通过泛型的方式解决这个问题
     *
     * @param e
     * @param <E>
     * @throws E
     */
    static <E extends Exception> void lambdaThrowException(MyException2 e) throws E {
        throw (E) e;
    }

    private void ger(User user) throws MyException2 {
        user.setEmail("cq");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new MyException2(0, "0000000000");
        }
        System.out.println(user);
    }

    private User getUser(User user) {
        Random random = new Random();
        random.nextInt(100);
        user.setName(random.nextInt() + "");
        return user;
    }

    /**
     * IntStream.range遍历
     */
    @Test
    public void t233() {
        JSONArray array = new JSONArray();
        IntStream.range(0, array.size()).mapToObj(array::getJSONObject).forEach(System.out::println);
    }

    /**
     * parallelStream并发的添加list会有丢数据问题，使用newCopyOnWriteArrayList解决
     */
    @Test
    public void t232() {
        ArrayList<Object> list = Lists.newArrayList();
        List<Object> list2 = Lists.newCopyOnWriteArrayList();
        list.parallelStream().forEach(list2::add);
    }

    /**
     * Optional处理null
     */
    @Test
    public void testOptional() {
        Optional<List<User>> list = this.get();
        list.ifPresent(System.out::println);
    }

    private Optional<List<User>> get() {
        User user = new User();
        user.setName("Cq");
        user.setId(1L);
        List<User> list = new ArrayList<>();
        list.add(user);
        if (CollUtil.isEmpty(list)) {
            System.out.println(true);
            return Optional.empty();
        }
        return Optional.of(list);
    }

    /**
     * Optional对null的处理
     */
    @Test
    public void trr() {
//        User user = this.getUser().orElseThrow(() -> new MyException(1, "用户不存在"));
//        System.out.println(user);
        this.getUser()
                .map(this::handel)
                .orElseThrow(() -> new MyException(1, "用户不存在"));
    }

    private User handel(User user) {
        user.setEmail(user.getEmail().toUpperCase());
        System.out.println(user);
        return user;
    }

    private Optional<User> getUser() {
        User user = new User();
        user.setEmail("cq");
        return Optional.ofNullable(user);
    }

    @Test
    public void fsfdas() {
        List<String> list = Arrays.asList("java", "python", "C++", "php", "java");
        //用LinkedList收集
        List<String> linkedListResult = list.stream().collect(Collectors.toCollection(LinkedList::new));
        linkedListResult.forEach(System.out::println);
        System.out.println("--------------");

        //用CopyOnWriteArrayList收集
        List<String> copyOnWriteArrayListResult = list.stream().collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        copyOnWriteArrayListResult.forEach(System.out::println);
        System.out.println("--------------");

        //用TreeSet收集
        TreeSet<String> treeSetResult = list.stream().collect(Collectors.toCollection(TreeSet::new));
        treeSetResult.forEach(System.out::println);
    }
}
