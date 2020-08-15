package org.zhire.work.service;



import java.util.Calendar;
import java.util.Date;

/**
 * @author  dingyingjie
 */
public class ToolUtils {


    /**
     * 计算生日
     * @param birthDay
     * @return
     * @throws
     */
    public static int getAgeByBirth(Date birthDay) throws Exception {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }





    public static  boolean ifCity(String province){
        switch (province){
            case "北京":
                return true;
            case "天津":
                return true;
            case "重庆":
                return  true;
            case "上海":
                return true;
            default:
                return false;
        }
    }

}
