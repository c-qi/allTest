package org.zhire.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.Person;
import org.junit.Test;
import org.zhire.pojo.User;
import org.zhire.utils.Item;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
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
    public void filter(){
        List<User> list = new ArrayList<>();
        List<User> expertList =
                list.stream().filter(l -> !l.getName().equals("cq")).collect(Collectors.toList());
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


    }
}
