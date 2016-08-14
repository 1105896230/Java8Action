package blook_class;

import six_class.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static blook_class.Dish.menu;
import static java.util.stream.Collectors.toList;
import static six_class.streamDemo.transactions;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class blookDemo {


    private void test() {
//        Transaction mostExpensive=transactions.get(0);
//        if (mostExpensive==null){
//            throw new IllegalAccessException("Empty list of transactions");
//        }
//        for (Transaction t:transactions.subList(1,transactions.size())){
//            if (t.getValue()>mostExpensive.getValue()){
//                mostExpensive=t;
//            }
//
//     }
    }

    private void test1() {

        Optional<Transaction> mostExpensive = transactions.stream().max(Comparator.comparing(Transaction::getValue));
    }

    private static void test2() {
        List<String> threehighCalorcDishNames = menu
                .stream()
                .filter(dish -> dish.getCalories() > 30)
                .map(Dish::getName).limit(3)
                .collect(toList());
        System.out.println(threehighCalorcDishNames);

    }

    private static void test3() {
        List<String> strings = Arrays.asList("Java8", "In", "Action");
        Stream<String> stream = strings.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    private static void test4() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    private static void test5() {
        List<Dish> dishesSkip2 =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .skip(2)
                        .collect(toList());
    }

    private static void test6() {
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(s -> s.split(""))
                .collect(toList());
        System.out.println(wordLengths);

        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);
    }
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish(){
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }

    private static void test7(){
        Optional<Dish> any = menu
                .stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
    public static void main(String[] args) {
        isVegetarianFriendlyMenu();
    }
}
