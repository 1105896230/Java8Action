package blook_class;

import six_class.Transaction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
//        List<Integer> wordLengths = words.stream()
//                .map(s -> s.split(""))
//                .collect(toList());
//        System.out.println(wordLengths);

        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct()
                .forEach(System.out::println);
    }

    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu() {
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    private static boolean isHealthyMenu2() {
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish() {
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }

    private static void test7() {
        Optional<Dish> any = menu
                .stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }

    private static void test8() {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

    }

    private static void test9() {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.parallelStream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }
    private static void test10() {
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();
    }
    private static void test11(){
        // Stream.of
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // Stream.empty
        Stream<String> emptyStream = Stream.empty();

        // Arrays.stream
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());

        // Stream.iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // fibonnaci with iterate
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                . map(t -> t[0])
                .forEach(System.out::println);

        // random stream of doubles with Stream.generate
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier(){
            public int getAsInt(){
                return 2;
            }
        }).limit(5)
                .forEach(System.out::println);


        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

        long uniqueWords = 0;
        try {
            uniqueWords = Files.lines(Paths.get("lambdasinaction/chap5/data.txt"), Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("There are " + uniqueWords + " unique words in data.txt");

    }
    public static void main(String[] args) {
        test11();
    }
}
