package date_class;

/**
 * Created by Administrator on 2016/8/13 0013.
 */

import java.util.Calendar;
import java.util.Date;

/**
 * 新的日期和时间API
 */
public class DateTimeExamples {
    public static void main(String[] args) {
        test1();
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
}
