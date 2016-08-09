package six_class;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static six_class.Dish.*;
/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class demo2 {
    public static void main(String[] args){
        test1();
    }
   private static void test1(){
       Long collect = menu.stream().collect(Collectors.counting());
       System.out.println(collect);

       Comparator<Dish> comparing = Comparator.comparing(Dish::getCalories);
       Optional<Dish> collect1 = menu.stream().collect(Collectors.maxBy(comparing));
       collect1.ifPresent(dish -> System.out.println(dish.getName()));
   }
}
