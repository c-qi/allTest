package org.zhire.demo;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class DistTest {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        List<String> list = menu.parallelStream()
                .filter(l -> l.getCalories() > 500)
                .limit(5)
                .sorted(Comparator.comparing(Dish::getCalories))
                // .collect(Collectors.toList());
                .map(Dish::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);

        System.out.println(menu.stream().collect(groupingBy(Dish::isVegetarian))); // 分组
        System.out.println(menu.stream().collect(summingInt(Dish::getCalories))); // 求和
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu);

        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get))); // 分组后选每组热量最高的
        System.out.println(mostCaloricByType);

    }

    @Test
    public void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        words.stream()
                .map(String::length)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


    @Test
    public void test3() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b); // 求和
        int sum2 = numbers.stream().reduce(0, Integer::sum);// 求和
        System.out.println(sum + " " + sum2);
        Optional<Integer> sum3 = numbers.stream().reduce((a, b) -> (a + b));// 没有初始值
        System.out.println(sum + " " + sum2 + " " + sum3);
        Optional<Integer> reduce = numbers.stream().reduce(Integer::max); // 求最小值
        System.out.println(reduce);

    }

}
