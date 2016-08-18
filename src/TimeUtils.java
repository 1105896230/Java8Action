import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class TimeUtils {
    public static final SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public static final Calendar calendar = Calendar.getInstance();
    private static long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
    private static long nh = 1000 * 60 * 60;//一小时的毫秒数
    private static long nm = 1000 * 60;//一分钟的毫秒数
    private static long ns = 1000;//一秒钟的毫秒数

    public static void main(String[] args) {
        String s1 = "2016-08-11T19:08:22.809919";
        System.out.print(isWithinWeek(s1));

    }

    public static long getCurrentTimes() {
        return System.currentTimeMillis();
    }

    //最后变化的时间
    public static String isWithinWeek(String modified) {
        long currentTimes = getCurrentTimes();
        try {
            Date d1 = DEFAULT_SDF.parse(modified);
            long time = d1.getTime();
            long day = (Math.abs(time - currentTimes)) / nd;
            if (day < 7)
                return getWeek(day, modified);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getmonth(modified) + "-" + getDay(modified);
    }

    private static String getWeek(long day, String modified) {
        if (day == 0)
            return "今天";
        try {
            int week = getWeek(modified);
            if (week == 0) {
                return "周日";
            } else if (week == 1) {
                return "周一";
            } else if (week == 2) {
                return "周二";
            } else if (week == 3) {
                return "周三";
            } else if (week == 4) {
                return "周四";
            } else if (week == 5) {
                return "周五";
            } else if (week == 6) {
                return "周六";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getmonth(modified) + "-" + getDay(modified);
    }

    public static int getWeek(String modified) throws ParseException {
        Date d1 = DEFAULT_SDF.parse(modified);
        calendar.setTime(d1);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static String getmonth(String modified) {
        return modified.substring(5, 7);
    }


    /**
     * @param modified 2016-08-10T09:59:04.956817 必须是这个格式或者前面符合年月日
     * @return
     */
    public static String getDay(String modified) {
        if (modified.length() > 10) {
            return modified.substring(8, 10);
        }
        return "";
    }
}
