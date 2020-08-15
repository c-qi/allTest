package org.zhire.work.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
public class DateUtils {

    public static Date today() {
        return new Date();
    }

    public static String todayStr() {
        return new SimpleDateFormat("yyyy-MM-dd").format(today());
    }

    public static String thatDayStr(long daysToSubtract) {
        return LocalDate.now().minusDays(daysToSubtract).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String todayFullStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(today());
    }

    public static String todayWeekDayStr() {
        return LocalDate.now().getDayOfWeek().toString();
    }

    public static int todayWeekDayInt() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    public static String dateStrWithPoint(String date) {
        return date.replaceAll("-", ".").substring(5, date.length());
    }

    public static String formattedDate(Date date) {
        return date != null ? new SimpleDateFormat("yyyy-MM-dd").format(date) : todayStr();
    }

    public static String formattedDate(long times) {
        return formattedDate("yyyy-MM-dd", times);
    }

    public static String formattedDate(String format) {
        return formattedDate(format, System.currentTimeMillis());
    }

    public static String formattedDate(String format, long times) {
        return new SimpleDateFormat(format).format(times);
    }

    public static String formattedDateTime(long times) {
        return formattedDate("yyyy-MM-dd HH:mm:ss", times);
    }

    public static String formattedDateTimeEx(long times) {
        return formattedDate("yyyy-MM-dd HH:mm:ss.SSS", times);
    }

    public static Long unixtime(String format, String formatDate) {
        long time = (new SimpleDateFormat(format)).parse(formatDate, new ParsePosition(0)).getTime();
        return time;
    }

    public static Long unixtime(String formatDate) throws ParseException {
        long time = DateFormat.getInstance().parse(formatDate).getTime();
        return time;
    }

    public static long currentDateTime() {
        return System.currentTimeMillis();
    }

    public static long currentDateTime(Duration duration) {
        return duration.toMillis();
    }

    public static Date longToDate(long millSec) {
        Date date = new Date(millSec);
        return date;
    }

    public static String getDateMonthDay(long times) {
        return formattedDate("MM月dd日", times);
    }

    /**
     * 获取两个时间相差天数（进位取整）
     *
     * @param sDate
     * @param eDate
     * @return
     */
    public static int getDateDayPoor(long sDate, long eDate) {
        BigDecimal nd = new BigDecimal(1000 * 24 * 60 * 60);
        BigDecimal diff = new BigDecimal(eDate - sDate);
        // 计算差多少天
        int day = diff.divide(nd, 0, BigDecimal.ROUND_UP).intValue();
        return day;
    }

    /**
     * 获取时间的月数
     *
     * @param birthday
     * @return
     */
    public static int getBirthdayMonth(long birthday) {
        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTimeInMillis(birthday);
        c2.setTime(new Date());

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 获取两个时间相差年数
     *
     * @param birthday
     * @return
     */
    public static int getBirthdayYear(long birthday) {
        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTimeInMillis(birthday);
        c2.setTime(new Date());

        result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 时间增加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static long dateAddDays(Long date, Integer days) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(date);
        c1.add(Calendar.DATE, days);
        return c1.getTimeInMillis();
    }

    /**
     * 当前时间增加秒数
     * @param seconds
     * @return
     */
    public static Date nowAddSeconds(Integer seconds) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(System.currentTimeMillis());
        c1.add(Calendar.SECOND, seconds);
        return c1.getTime();
    }
    /**
     * 获取时间戳的零点时间戳
     *
     * @param date
     * @return
     */
    public static Long getTodayZeroPointTimestamps(Long date) {
        Long oneDayTimestamps = Long.valueOf(60 * 60 * 24 * 1000);
        return date - (date + 60 * 60 * 8 * 1000) % oneDayTimestamps;
    }

    /**
     * 判断日期是否是当前日期
     *
     * @param date
     * @return
     */
    public static boolean dateIsToday(Long date) {
        String now = todayStr();
        String dateStr = formattedDate(date);
        return now.equals(dateStr);
    }

    /**
     * 由过去的某一时间,计算距离当前的时间
     */
    public static String calculateTime(Long time) throws ParseException {
        // 获取当前时间的毫秒数
        long nowTime = System.currentTimeMillis();
        String msg = null;
        // 指定时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String datetime = sdf.format(new Date(time));

        // 指定时间
        Date setTime = null;
        try {
            // 将字符串转换为指定的时间格式
            setTime = sdf.parse(datetime);
        } catch (ParseException e) {
            throw e;
        }
        // 获取指定时间的毫秒数
        long reset = setTime.getTime();
        long dateDiff = nowTime - reset;
        if (dateDiff < 0) {
            msg = "输入的时间不对";
        } else {
            // 秒
            long dateTemp1 = dateDiff / 1000;
            // 分钟
            long dateTemp2 = dateTemp1 / 60;
            // 小时
            long dateTemp3 = dateTemp2 / 60;
            // 天数
            long dateTemp4 = dateTemp3 / 24;
            // 月数
            long dateTemp5 = dateTemp4 / 30;
            // 年数
            long dateTemp6 = dateTemp5 / 12;
            if (dateTemp6 > 0) {
                msg = dateTemp6 + "年前";
            } else if (dateTemp5 > 0) {
                msg = dateTemp5 + "个月前";
            } else if (dateTemp4 > 0) {
                msg = dateTemp4 + "天前";
            } else if (dateTemp3 > 0) {
                msg = dateTemp3 + "小时前";
            } else if (dateTemp2 > 0) {
                msg = dateTemp2 + "分钟前";
            } else if (dateTemp1 > 0) {
                msg = "刚刚";
            }
        }
        return msg;
    }

    public static Map<String, String> getWeekDate() {
        Map<String, String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        System.out.println("所在周星期一的日期：" + weekBegin);


        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
        System.out.println("所在周星期日的日期：" + weekEnd);

        map.put("mondayDate", weekBegin);
        map.put("sundayDate", weekEnd);
        return map;
    }

    /**
     * 当前日期加上天数后的日期
     *
     * @param num    为增加的天数
     * @param format 日期格式:yyyy-MM-dd或yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String plusDays(String format, int num) {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);
        d = ca.getTime();
        String enddate = sf.format(d);
        return enddate;
    }

    public static Long getTodayMill() {
        LocalDateTime today = LocalDateTime.now().withHour(00).withMinute(00).withSecond(00);
        long milli = today.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return milli;
    }

    public static Long getMonthFirstDateMill() {
        LocalDateTime firstDay = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())
                .withHour(00).withMinute(00).withSecond(00);
        long milli = firstDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return milli;
    }

    public static Long getMonthLastDateMill() {
        LocalDateTime firstDay = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23).withMinute(59).withSecond(59);
        long milli = firstDay.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return milli;
    }


}
