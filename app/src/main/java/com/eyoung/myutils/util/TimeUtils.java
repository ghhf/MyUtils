package com.eyoung.myutils.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Author: created by ghappy on 2018/11/7 15:12
 * <p>
 * Description: 时间工具类
 */
public class TimeUtils {
    // 获取某年某月的第一天日期
    public static Date getStartMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    // 获取某年某月的最后一天日期
    public static Date getEndMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    // 获取本月第一天0：00的时间
    public static long getTimesMonthMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTimeInMillis();
    }
}
