package org.zhire.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.zhire.pojo.User;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenqi
 * @Date: 2020.1.6 10:30
 */
public class Java8DateTest {

    @org.junit.Test
    public void dateTest() {
        LocalDate date = LocalDate.now();
        System.out.println("当前日期=" + date);

        LocalDate date2 = LocalDate.of(2000, 1, 1);
        System.out.println("千禧年=" + date2);

        LocalDate date3 = LocalDate.now();
        System.out.printf("年=%d， 月=%d， 日=%d", date3.getYear(), date3.getMonthValue(), date3.getDayOfMonth());

        LocalDate now = LocalDate.now();
        LocalDate date4 = LocalDate.of(2018, 9, 24);
        System.out.println("日期是否相等=" + now.equals(date4));

        LocalTime time = LocalTime.now();
        System.out.println("当前时间=" + time);

        // 时间增量
        LocalTime time2 = LocalTime.now();
        LocalTime newTime = time2.plusHours(2);
        System.out.println("newTime=" + newTime);

        // 日期增量
        LocalDate date5 = LocalDate.now();
        LocalDate newDate = date5.minus(1, ChronoUnit.DAYS);
        System.out.println("newDate=" + newDate);
        String startTime = "";
        String endTime = "";
        startTime = newDate.toString() + " 00:00:00";
        endTime = newDate.toString() + " 23:59:59";
        System.out.println(startTime);
        System.out.println(endTime);

        // 解析日期
        String dateText = "20180924";
        LocalDate date6 = LocalDate.parse(dateText, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("格式化之后的日期=" + date6);

        // 格式化日期
        dateText = date6.format(DateTimeFormatter.ISO_DATE);
        System.out.println("dateText=" + dateText);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 日期时间转字符串
        LocalDateTime now2 = LocalDateTime.now();
        String nowText = now2.format(formatter);
        System.out.println("nowText=" + nowText);

        // 字符串转日期时间
        String datetimeText = "1999-12-31 23:59:59";
        LocalDateTime datetime = LocalDateTime.parse(datetimeText, formatter);
        System.out.println(datetime);

        // Instant         时间戳
        //Duration        持续时间、时间差
        //LocalDate       只包含日期，比如：2018-09-24
        //LocalTime       只包含时间，比如：10:32:10
        //LocalDateTime   包含日期和时间，比如：2018-09-24 10:32:10
        //Peroid          时间段
        //ZoneOffset      时区偏移量，比如：+8:00
        //ZonedDateTime   带时区的日期时间
        //Clock           时钟，可用于获取当前时间戳
        //java.time.format.DateTimeFormatter      时间格式化类

        String s = "2020-02-05 11:19:38";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(s, dtf);
        LocalDate localDate = parse.toLocalDate();
        System.out.println(parse);

    }

    @org.junit.Test
    public void datePlus() throws Exception {
        String dateCycleEnd = new DateTime("2020-12-01").plusMonths(1).toString("yyyy-MM-dd");
        System.out.println(dateCycleEnd);

        org.joda.time.format.DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        DateTime dateTime = DateTime.parse("2018-4-23 23:12:16", format);
        System.out.println(dateTime);

        DateTime time = new DateTime();
        System.out.println(time);
        System.out.println(time.toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println();

        Date date = time.toDate();
        System.out.println(date);

        DateTime time2 = new DateTime(System.currentTimeMillis());
        System.out.println(time2.getMillis());

        String startMonth = new DateTime().minusMonths(1).toString("yyyy-MM-01");
        String endMonth = new DateTime().minusMonths(1).toString("yyyy-MM-31");
        System.out.println(startMonth);
        System.out.println(endMonth);

        DateFormat startFormat = new SimpleDateFormat("yyyy-MM-01");
        Date parse = startFormat.parse(startMonth);
        System.out.println(parse);

    }


    public static String getLastMonthFirstDay() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(sdf.format(date));

        int maxCurrentMonthDay = 0;
        // Calendar calendar = Calendar.getInstance();
        // System.out.println("当前时间: " + sdf.format(calendar.getTime()));


        Calendar calendar5 = Calendar.getInstance();
        maxCurrentMonthDay = calendar5.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar5.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
        calendar5.set(Calendar.DAY_OF_MONTH, 1);
        String s = sdf.format(calendar5.getTime());
        // System.out.println("上月第一天: " + sdf.format(calendar5.getTime()));
        return s;

    }

    private static String getLastMonthLastDay() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(sdf.format(date));

        int maxCurrentMonthDay = 0;
        // Calendar calendar = Calendar.getInstance();
        Calendar calendar6 = Calendar.getInstance();
        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar6.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar6.set(Calendar.DAY_OF_MONTH, maxCurrentMonthDay);
        String s = sdf.format(calendar6.getTime());
        // System.out.println("上月最后一天: " + sdf.format(calendar6.getTime()));
        return s;
    }



}
