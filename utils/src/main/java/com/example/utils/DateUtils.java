package com.example.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

public class DateUtils {

    public static final String Y4M2D2 = "yyyy-MM-dd";
    public static final String YMD = "yyyy.MM.dd";
    public static final String YMDMS = "yyyyMMddHHmmss";
    public static final String FORMATPATTERN3 = "yyyy-MM-dd HH:mm:ss";
    public static final String M2D2 = "MM-dd";
    public static final String FORMATPATTERN2 = "yyyy-MM-dd HH:mm";
    public static final String Y4M2 = "yyyy年MM月";
    public static final Locale LOCALE = Locale.CHINA;

    /**
     * 计算两个日期相差的天数
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return
     */
    public static int getIntervalDay(Calendar start, Calendar end) {
        double daycount = Math.ceil(
                (end.getTimeInMillis() - start.getTimeInMillis()) * 1.0 / (1000 * 3600 * 24));
        return (int) daycount + 1;
    }

    public static Date parse2date(final String pattern, final String datestr)
            throws ParseException {
        if (TextUtils.isEmpty(datestr))
            return null;
        SimpleDateFormat datafm = new SimpleDateFormat(pattern, LOCALE);
        return datafm.parse(datestr);
    }

    public static Calendar parse2calendar(final String pattern, final String date_str) {

        SimpleDateFormat datafm = new SimpleDateFormat(pattern, LOCALE);
        Calendar result = getCalendarInstance();
        try {
            result.setTime(datafm.parse(date_str));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static Calendar getCalendarInstance() {
        Calendar c = Calendar.getInstance(LOCALE);
        setMidnight(c);
        return c;
    }

    public static Calendar getCalendarInstance(Calendar clone) {
        Calendar c;
        c = Calendar.getInstance(LOCALE);
        setMidnight(c);
        c.setTime(clone.getTime());
        return c;
    }

    public static Calendar getCalendarInstance(Date clone) {
        Calendar c = Calendar.getInstance(LOCALE);
        c.setTime(clone);
        setMidnight(c);
        return c;
    }

    public static Calendar get3MonthAfterCaleandar() {
        Calendar c = Calendar.getInstance(LOCALE);
        setMidnight(c);
        c.add(Calendar.MONTH, 3);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_YEAR, -1);
        return c;
    }

    // public static Calendar getThreeMonthAfter(final Calendar cal) {
    // Calendar c = Calendar.getInstance(LOCALE);
    // c.setTime(cal.getTime());
    // c.add(Calendar.MONTH, 3);
    // c.set(Calendar.DAY_OF_MONTH, 1);
    // c.add(Calendar.DAY_OF_YEAR, -1);
    // return c;
    // }

    public static Calendar add3MonthLater(final Calendar cal) {
        Calendar c = Calendar.getInstance(LOCALE);
        c.setTime(cal.getTime());
        c.add(Calendar.MONTH, 3);
        return c;
    }

    public static Calendar addMothsLater(final Calendar cal, final int monthCount) {
        Calendar c = Calendar.getInstance(LOCALE);
        c.setTime(cal.getTime());
        c.add(Calendar.MONTH, monthCount);
        return c;
    }

    /**
     * 将参数日期加n，提取字符串
     *
     * @param targetcal
     * @return
     */
    public static String addNDaytoStr(final String pattern, Calendar targetcal, int n) {
        Calendar nc = getCalendarInstance(targetcal);
        nc.add(Calendar.DAY_OF_YEAR, n);
        SimpleDateFormat datafm = new SimpleDateFormat(pattern, Locale.CHINESE);
        return datafm.format(nc.getTime());
    }

    public static String addNDaytoStr(final String pattern, Date targetcal, int n) {
        Calendar nc = Calendar.getInstance(Locale.CHINESE);
        nc.setTime(targetcal);
        nc.add(Calendar.DAY_OF_MONTH, n);
        SimpleDateFormat datafm = new SimpleDateFormat(pattern, Locale.CHINESE);
        return datafm.format(nc.getTime());
    }

    /**
     * @param
     * @return
     * @throws
     * @Title: formateDate2Str
     * @Description: 格式化日期输出
     */
    public static String formateDate2Str(String parserPattern, String targetPattern,
                                         String datestr) {

        SimpleDateFormat datefm = new SimpleDateFormat(targetPattern, Locale.CHINESE);
        Date tdate = null;
        try {
            tdate = parse2date(parserPattern, datestr);
            return tdate == null ? null : datefm.format(tdate);
        } catch (ParseException e) {
            return datestr;
        }
    }

    public static String formateDateStr(String datestr, int nightcount) {
        if (nightcount == 0) {
            nightcount = 1;
        }

        SimpleDateFormat datefm = new SimpleDateFormat("M月d日(E)", Locale.CHINESE);
        Date tdate;
        Calendar cdar = new GregorianCalendar();
        try {
            tdate = parse2date("yyyy-MM-dd", datestr);

            cdar.setTime(tdate);
            cdar.add(Calendar.DAY_OF_MONTH, nightcount);
        } catch (ParseException e) {
            tdate = new Date();
        }

        return datefm.format(cdar.getTime());
    }

    public static String formateDateStr(String datestr) {
        SimpleDateFormat datefm = new SimpleDateFormat("M月d日(E)", Locale.CHINESE);
        Date tdate;
        try {
            tdate = parse2date("yyyy-MM-dd", datestr);
        } catch (ParseException e) {
            tdate = new Date();
        }

        return datefm.format(tdate);
    }

    public static String getCalendarString(final String pattern, final Calendar cldar) {
        SimpleDateFormat datefm = new SimpleDateFormat(pattern, LOCALE);
        return datefm.format(cldar.getTime());
    }

    public static String getDateString(final String pattern, final Date date) {
        SimpleDateFormat datefm = new SimpleDateFormat(pattern, LOCALE);
        return datefm.format(date);
    }

    /**
     * @param @param  time 满足 20140531120000这个规则的日期字符串
     * @param @return
     * @return int 返回类型
     * @throws
     * @Title: getRemainDays
     * @Description:(根据给定日期字符串返回剩余天数，进一法，大于一天算两天)
     */
    public static long getRemainDays(String time) {
        // 20140531120000
        if (time == null)
            return -1;
        if (time.length() != 14)
            return -1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss", Locale.CHINESE);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long end = cal.getTimeInMillis();
        long between = end - System.currentTimeMillis();
        if (between <= 0) {
            return 0;
        } else {
            long day = between / (24 * 60 * 60 * 1000);
            // final long hour = (between / (60 * 60 * 1000) - day * 24);
            // final long min = ((between / (60 * 1000)) - day * 24 * 60 - hour
            // * 60);
            // final long sec = (between / 1000 - day * 24 * 60 * 60
            // - hour * 60 * 60 - min * 60);
            final long least = between % (24 * 60 * 60 * 1000);
            if (1000 < least) {
                day++;
            }
            return day;
        }
    }

    /**
     * @param @param  startDate
     * @param @param  endDate
     * @param @return
     * @return int -1-即将开始， 0进行中 ， 1已结束
     * @throws
     * @Title: compareTime
     * @Description:
     */
    public static int compareTime(String format, String startDate, String endDate) {
        // 20140531120000
        if (startDate == null || endDate == null)
            return 1;
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        Calendar cal = Calendar.getInstance();
        long start = 0, end = 0;
        try {
            cal.setTime(sdf.parse(startDate));
            start = cal.getTimeInMillis();
            cal.setTime(sdf.parse(endDate));
            end = cal.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long current = System.currentTimeMillis();
        if (current < start) {
            return -1;
        } else if (current > start && current < end) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void setMidnight(Calendar cal) {
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
    }

    public static String formatMonthDate(Date date) {
        SimpleDateFormat datefm = new SimpleDateFormat("MM月dd日", Locale.CHINESE);
        String str = datefm.format(date);
        return str;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat datefm = new SimpleDateFormat("yy年MM月", Locale.CHINESE);
        String str = datefm.format(date);
        return str;
    }


    public static Date setMidnightDate(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);

        return cal.getTime();

    }

    public static boolean equalsDates(Date date1, Date date2) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date1);
        c2.setTime(date2);

        return c1.get(MONTH) == c2.get(MONTH) && c1.get(YEAR) == c2.get(YEAR) &&
                c1.get(DAY_OF_MONTH) == c2.get(DAY_OF_MONTH);

    }

    /**
     * 计算相差时间
     *
     * @return
     */
    public static long subTime(Calendar c1, Calendar c2) {
        long time_a = c1.getTime().getTime();
        long time_b = c2.getTime().getTime();
        long result = time_b - time_a;
        if (result > 0) {
            return result / 1000L;
        } else {
            return 0;
        }
    }

    public static String dateToDate(String date, String oldPattern, String newPattern) {
        if (date == null || oldPattern == null || newPattern == null)
            return "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern);        // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern);        // 实例化模板对象
        Date d = null;
        try {
            d = sdf1.parse(date);   // 将给定的字符串中的日期提取出来
        } catch (Exception e) {            // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace();       // 打印异常信息
        }
        return sdf2.format(d);
    }

    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 将时间字符串转Date
     *
     * @param pattern
     * @param dateStr
     * @return
     */
    public static Date transDate(String pattern, String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        Date date;
        if (!TextUtils.isEmpty(dateStr)) {
            try {
                date = sdf.parse(dateStr);
                long l = date.getTime();
                date.setTime(l);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date();
    }

    /**
     * 将时间转字符串
     *
     * @param pattern
     * @param date
     * @return
     */
    public static String patternDate(String pattern, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * @param patternOut
     * @param patternIn
     * @param offsetD
     * @param dateStr
     * @return
     */
    public static String patternDate(String patternOut, String patternIn, int offsetD,
                                     String dateStr) {
        Date date = transDate(patternIn, dateStr);
        long l = date.getTime();
        l = l - offsetD * 24 * 60 * 60 * 1000;
        date.setTime(l);
        return patternDate(patternOut, date);
    }

    /**
     * 根据日期得到今天、昨天==
     *
     * @param dateStr
     * @return
     */
    public static String getYesOrToday(String dateStr) {
        try {
            String ret = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long create = sdf.parse(dateStr).getTime();
            Calendar now = Calendar.getInstance();
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));//毫秒数
            long ms_now = now.getTimeInMillis();
            if (ms_now - create < ms) {
                ret = "今天";
            } else if (ms_now - create < (ms + 24 * 3600 * 1000)) {
                ret = "昨天";
            }/*else if(ms_now-create<(ms+24*3600*1000*2)){
                ret = "前天";
            }else{
                ret= "更早";
            }*/
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比较时间大小
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare_date(String format, String date1, String date2) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取星期几
     * @param calendar
     * @return
     */
    public static String getDayOfWeek(Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                return "星期一";
            case Calendar.TUESDAY:
                return "星期二";
            case Calendar.WEDNESDAY:
                return "星期三";
            case Calendar.THURSDAY:
                return "星期四";
            case Calendar.FRIDAY:
                return "星期五";
            case Calendar.SATURDAY:
                return "星期六";
            case Calendar.SUNDAY:
            default:
                return "星期日";
        }

    }
}
