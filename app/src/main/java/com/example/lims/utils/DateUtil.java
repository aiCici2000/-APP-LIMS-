package com.example.lims.utils;

/**
 * @Author：李壮
 * @Package：com.example.lims.utils
 * @Date：2023/1/17 16:32
 * Describe:
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {
    /**
     * 指定时间格式
     */
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static DateFormat dateFormat_short = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 返回当前时间的指定字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getTime() {
        String format = dateFormat.format(new Date());
        return format;
    }

    /**
     * 返回指定时间戳的指定字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getTime(long l) {
        String format = dateFormat.format(l);
        return format;
    }

    /**
     * 返回指定时间的指定字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getTime(Date date) {
        String format = dateFormat.format(date);
        return format;
    }

    /**
     * 返回指定时间的指定字符串格式 yyyy-MM-dd
     */
    public static String getTimeShort(Date date) {
        String format = dateFormat_short.format(date);
        return format;
    }

    /**
     * 通过指定的字符串格式返回指定日期
     */
    public static Date getTime(String str) {
        Date parse = new Date();
        try {
            parse = dateFormat.parse(str);
            return parse;
        } catch (ParseException e) {
            System.out.println("请输入正确的日期格式:\"yyyy/MM/dd HH:mm:ss\"");
        }
        return null;
    }

    /**
     * 在原日期的基础上增加天数
     *
     * @param date
     * @param i
     * @return
     */
    public static Date addDay(Date date,int i){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, i);
        Date newDate = c.getTime();
        return newDate;
    }

    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    //TODO Android原生，月 0-11
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }
}
