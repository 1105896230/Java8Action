package four_class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static four_class.Dish.menu;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class streamDemo {

    public static void main(String[] args) {
        test4();
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
}
