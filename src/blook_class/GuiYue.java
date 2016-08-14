package blook_class;

import java.util.IntSummaryStatistics;

import static blook_class.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class GuiYue {
    public static void main(String[] args) {
        test1();
    }

    private static void test() {
        Integer collect = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.print(collect);
    }

    private static void calculateAverageCalories() {
        Double collect = menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static void calculateMenuStatistics() {
        IntSummaryStatistics collect = menu.stream().collect(summarizingInt(Dish::getCalories));
        double average = collect.getAverage();
        long count = collect.getCount();
    }

    private static void test1() {

        String collect = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(collect);
        String collec1t = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(collec1t);

    }


}
