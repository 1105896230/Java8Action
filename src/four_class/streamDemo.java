package four_class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static four_class.Dish.menu;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class streamDemo {

    public static void main(String[] args) {
        test9();
    }

    /**
     * 获取菜名
     */
    private static void test() {
        List<String> menu_names = menu.stream().
                filter(dish -> dish.getCalories() > 300).
                map(Dish::getName).
                limit(3).collect(Collectors.toList());
        System.out.print(menu_names.toString());
    }

    /**
     * 筛选各异的数字
     */
    private static void test1() {
        List<Integer> collect = Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().filter(integer -> integer % 2 == 0).distinct().collect(Collectors.toList());
        System.out.print(collect.toString());
    }
    /**
     * 跳过元素
     */
    private static void test2() {
        List<Integer> collect = Arrays.asList(1, 2, 1, 3, 3, 2, 4).
                stream().skip(3).collect(Collectors.toList());
        System.out.print(collect.toString());
    }

    /**
     * 检查是否匹配到元素，如果是对象需要重写equal hasCode方法
     * anyMatch 检查是否匹配到一个元素
     * allMatch 匹配所有元素
     * noneMatch 没有匹配到
     */
    private static void test3() {
        List<Integer> integers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        boolean b = integers.
                stream().anyMatch(integer -> integer % 2 == 0);
        System.out.println(b+"");

        boolean c = integers.
                stream().allMatch(integer -> integer /1 == integer);
        System.out.println(c+"");

        boolean d = integers.
                stream().noneMatch(integer -> integer /1 ==integer);
        System.out.println(d+"");
    }

    /**
     * 查找元素,findAny()查找元素 ，可以结合fitlte等
     * 返回一个Optional 包含了一个数值 是否存在和不存在 避免了空指针
     * isPresent 判断是否有数值
     * ifPresenter（Consumer<T> bolck</>）传递一个代码块
     * T get() 当数值存在的时候 返回，没有回抛出NoSuchElment 异常
     * T orElse（T other) 但数值存在的时候返回，没有返回一个默认值
     */
    private static void test4(){
        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();
        boolean present = any.isPresent();
        System.out.print(present);
        any.ifPresent(dish -> System.out.print(dish.getName()));
    }

    /**
     * 归纳
     */
    private static void test5(){
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }

    /**
     * map和flatmap的区别
     */
    private static void test6(){
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);

        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);

    }
    /**
     * 避免自动装箱
     * mapToInt
     * mapToDouble 等 避免了自动装箱的问题，同时提供sum等便捷的方法
     */
    private static void test7(){
        int value =menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.print(value);
    }

    /**
     * 数值流转为为对象流
     */
    private static void test8(){
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();//将数值流转换为stream
    }

    /**
     * 数值范围
     */
    private static void test9(){
//        他的静态方法
        IntStream intStream = IntStream.rangeClosed(1, 100).filter(value -> value % 2 == 0);
        System.out.print(intStream.count());
    }
    /**
     * 由数值创建流
     */
    private static void test10(){
        Stream<String> java = Stream.of("java", "ios");
        Stream<Object> empty = Stream.empty();
    }
}
