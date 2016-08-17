package optional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class OptionalMain {
    public static final SimpleDateFormat YYYY_MM_DD_DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
    private static long nh = 1000 * 60 * 60;//一小时的毫秒数
    private static long nm = 1000 * 60;//一分钟的毫秒数
    private static long ns = 1000;//一秒钟的毫秒数

    public static void main(String[] args) {
//        Optional<Car> opCar = Optional.empty();
//        Car car = null;
//        Optional.of(car);
//        Optional<Car> car1 = Optional.ofNullable(car);
//        Person person = null;
//        Optional<Person> person1 = Optional.ofNullable(person);
//        String carInsuranceName = getCarInsuranceName(person1);
//        System.out.print(carInsuranceName);
        test("2016-08-25");
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .get();
    }


    private static void test(String checkIn) {
        long currentTimes = System.currentTimeMillis();
        try {
            Date d1 = YYYY_MM_DD_DEFAULT_SDF.parse(checkIn);
            long time = d1.getTime();
            if (currentTimes <= time) {
                long hour = (time - currentTimes) / nd;
                System.out.print(hour);
            } else if (currentTimes > time) {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
