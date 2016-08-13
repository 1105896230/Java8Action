package date_class;

/**
 * Created by Administrator on 2016/8/13 0013.
 */

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * 新的日期和时间API
 */
public class DateTimeExamples {
    public static void main(String[] args) {
        useDateFormatter();
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
        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant now = instant.now();
        Instant instant1 = instant.ofEpochSecond(3, 0);
        //2秒之后再加上1秒
        Instant instant2 = instant.ofEpochSecond(2, 1_000_000_000);
        //4秒之前在减去1秒
        Instant instant3 = instant.ofEpochSecond(4, -1_000_000_000);

//        会抛出异常
        int i = instant.get(ChronoField.DAY_OF_MONTH);
    }

    private static void test8() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(lastDayOfMonth());
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);
        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);

    }


    private static class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }

    }

    private static void useDateFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(date.format(formatter));
        System.out.println(date.format(italianFormatter));

        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        System.out.println(date.format(complexFormatter));
    }
}