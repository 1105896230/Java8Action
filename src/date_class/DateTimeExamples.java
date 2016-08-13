package date_class;

/**
 * Created by Administrator on 2016/8/13 0013.
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * 新的日期和时间API
 */
public class DateTimeExamples {
    public static void main(String[] args) {
        test4();
    }


    /**
     * java1.0 对时间的类的处理
     */
    private static void test() {
        Date date = new Date(114, 2, 18);
        System.out.println(date);
    }

    /**
     * java1.1 使用日历的类 对时间的类的处理
     */
    private static void test1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.FEBRUARY, 18);
        System.out.println(calendar);
    }

    /**
     * java8引入新的时间日期和时间库
     */
    private static void test2() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int i = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();
        System.out.println(year);
        System.out.println(month);

        System.out.println(dayOfMonth);

        System.out.println(dayOfWeek);
        System.out.println(i);
        System.out.println(leapYear);


    }

    /**
     * localTime ,来描述一天的是时间
     */
    private static void test3() {
        LocalTime time = LocalTime.of(13, 45, 20); // 13:45:20
        int hour = time.getHour(); // 13
        int minute = time.getMinute(); // 45
        int second = time.getSecond(); // 20
        System.out.println(time);
    }

    /**
     * ChronoField 来读取LocalDate里面的数据
     */
    private static void test4() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(y);
        System.out.println(m);
        System.out.println(d);
    }
}
