package date_class;

/**
 * Created by Administrator on 2016/8/13 0013.
 */

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * 新的日期和时间API
 */
public class DateTimeExamples {
    public static void main(String[] args) {
        test7();
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


    /**
     * LocalDateTime 复合类 ，是LocalDate和LocalTime的合体
     */
    private static void test5() {
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20); // 2014-03-18T13:45
        System.out.println(dt1);
    }

    /**
     * LocalDateTime 复合类 可以根据LocalDatime 获取 localTime 和localDate
     */
    private static void test6() {
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20); // 2014-03-18T13:45
        System.out.println(dt1);

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);
    }


    /**
     * Instant 是让计算器去理解时间 ，可以获取时间戳等
     * 根据毫秒去生存时间 他去生成时间
     */
    private static void test7() {

    }
}
