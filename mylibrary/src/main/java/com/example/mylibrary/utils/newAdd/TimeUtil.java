package com.example.mylibrary.utils.newAdd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {
    private static int MILL_MIN = 1000 * 60;
    private static int MILL_HOUR = MILL_MIN * 60;
    private static int MILL_DAY = MILL_HOUR * 24;

    private static String JUST_NOW = "刚刚";
    private static String MIN = "分钟前";
    private static String HOUR = "小时前";
    private static String DAY = "天前";
    private static String MONTH = "月前";
    private static String YEAR = "年前";

    private static String YESTER_DAY = "昨天";
    private static String THE_DAY_BEFORE_YESTER_DAY = "前天";
    private static String TODAY = "今天";

    private static String DATE_FORMAT = "MM-dd HH:mm";
    private static String YEAR_FORMAT = "yyyy-MM-dd";
    private static String CHINESE_YEAR_FORMAT = "yyyy年MM月dd日";

    private static Calendar msgCalendar = null;
    private static SimpleDateFormat dayFormat = null;
    private static SimpleDateFormat dateFormat = null;
    private static SimpleDateFormat yearFormat = null;

    public static String parseTime(long seconds) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = new Date(seconds * 1000);
        return df.format(d);
    }

    public static String parseTime(String seconds) {
        try {
            long second = Long.valueOf(seconds);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d = new Date(second * 1000);
            return df.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "---";
    }

    public static String parseTimeYMD(long seconds) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date(seconds * 1000);
        return df.format(d);
    }

    public static String parseTimeYMDDot(String seconds) {
        try {
            long second = Long.valueOf(seconds);
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            Date d = new Date(second * 1000);
            return df.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "---";
    }


    public static String getFormatDateTime(Context context) {
        return DateUtils.formatDateTime(context,
                System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getCurrentYMDHM() {//获取系统时间年与日
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy年MM月dd日    HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    public static String TimeTransverter(String time) {
        time = time.replace("Z", "+0000");
        SimpleDateFormat formatDate = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ", Locale.CHINA);
        Date date;
        try {
            date = formatDate.parse(time);
            formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            time = formatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String TimeTransverterYMD(String time) {
        time = time.replace("Z", "+0000");
        SimpleDateFormat formatDate = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ", Locale.CHINA);
        Date date;
        try {
            date = formatDate.parse(time);
            formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            time = formatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 转换为2015年01月11日格式
     *
     * @param seconds 秒
     * @return 格式化之后的字符串
     */
    public static String timeFormatYMD(long seconds) {
        String resultTime = parseTimeYMD(seconds);
        String[] strings = resultTime.split("-");
        if (strings.length == 3) {
            resultTime = strings[0] + "年" + strings[1] + "月" + strings[2] + "日";
        }
        return resultTime;
    }

    public static String parseTimeYMD(String seconds) {
        try {
            long second = Long.valueOf(seconds);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date(second * 1000);
            return df.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "---";

    }

    /**
     * 转换为2015年01月11日格式
     *
     * @param seconds 秒
     * @return 格式化之后的字符串
     */
    public static String timeFormatMD(long seconds) {
        String resultTime = parseTimeYMD(seconds);
        String[] strings = resultTime.split("-");
        if (strings.length == 3) {
            resultTime = strings[1] + "月" + strings[2] + "日";
        }
        return resultTime;
    }

    public static String timeformat(String time) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(date);
        String[] arr1 = time.split("-");
        String[] arr2 = nowTime.split("-");
        String[] arr3 = time.split(" ");
        String[] arr4 = nowTime.split(" ");
        if (!arr1[0].equals(arr2[0])) {
            time = arr3[0];
        } else {
            if (!arr3[0].equals(arr4[0])) {
                String[] arr5 = arr1[2].split(" ");
                time = arr1[1] + "月" + arr5[0] + "日";
            } else {
                time = "今天" + arr3[1];
            }
        }
        return time;
    }

    /**
     * 消息里的时间
     *
     * @param chartTime
     * @return
     */
    public static String msgTimeFormat(long chartTime) {
        long nowTimestamp = getCurrentTime();
        Time nowTime = new Time(); // yyyy-MM-dd HH:mm
        Time msgTime = new Time();
        msgTime.set(chartTime * 1000);
        nowTime.setToNow();
        int[] nowTimeInt = new int[5];
        int[] msgTimeInt = new int[5];
        nowTimeInt[0] = nowTime.year;
        msgTimeInt[0] = msgTime.year;
        nowTimeInt[1] = nowTime.month;
        msgTimeInt[1] = msgTime.month;
        nowTimeInt[2] = nowTime.monthDay;
        msgTimeInt[2] = msgTime.monthDay;
        nowTimeInt[3] = nowTime.hour;
        msgTimeInt[3] = msgTime.hour;
        nowTimeInt[4] = nowTime.minute;
        msgTimeInt[4] = msgTime.minute;
        String backString = "";
        if (nowTimeInt[0] == msgTimeInt[0]) { // 同年
            if (nowTimeInt[1] == msgTimeInt[1]) {// 同月
                if (nowTimeInt[2] == msgTimeInt[2]) { // 同天
                    if (nowTimeInt[3] == msgTimeInt[3]) { // 同小时
                        if (nowTimeInt[4] == msgTimeInt[4]) { // 同分钟
                            backString = "刚刚";
                        } else {
                            double nearMinute = Math
                                    .floor((nowTimestamp - chartTime) / 60);
                            if (nearMinute <= 0) {
                                backString = "刚刚";
                            } else {
                                backString = (int) nearMinute + "分钟前";
                            }
                        }
                    } else {
                        backString = "今天  "
                                + parseTimeFormat(chartTime, "HH:mm");
                    }
                } else if (nowTimeInt[2] - msgTimeInt[2] == 1) {
                    backString = "昨天 " + parseTimeFormat(chartTime, "HH:mm");
                } else {
                    backString = parseTimeFormat(chartTime, "MM-dd HH:mm");
                }
            } else {
                backString = parseTimeFormat(chartTime, "MM-dd HH:mm");
            }
        } else {
            backString = parseTimeFormat(chartTime, "yyyy-MM-dd HH:mm");
        }

        return backString;
    }


    /**
     * 微博里的时间
     *
     * @param chartTime
     * @return
     */
    public static String weiboTimeFormat(long chartTime) {
        Time nowTime = new Time(); // yyyy-MM-dd HH:mm
        Time weiboTime = new Time();
        weiboTime.set(chartTime * 1000);
        nowTime.setToNow();
        int[] nowTimeInt = new int[5];
        int[] msgTimeInt = new int[5];
        nowTimeInt[0] = nowTime.year;
        msgTimeInt[0] = weiboTime.year;
        nowTimeInt[1] = nowTime.month;
        msgTimeInt[1] = weiboTime.month;
        nowTimeInt[2] = nowTime.monthDay;
        msgTimeInt[2] = weiboTime.monthDay;
        String backString = "";
        if (nowTimeInt[0] == msgTimeInt[0] && nowTimeInt[1] == msgTimeInt[1]
                && nowTimeInt[2] == msgTimeInt[2]) {// 同一天 今天 10:00；
            backString = "今天 " + parseTimeFormat(chartTime, "HH:mm");
        } else { // 不同天   2015-12-12
            backString = parseTimeFormat(chartTime, "yyyy-MM-dd");
        }
        return backString;
    }

    /**
     * 课程时间格式
     *
     * @param liveTime
     * @return
     */
    public static String liveTimeFormat(long liveTime) {
        long nowTimestamp = getCurrentTime();
        Time nowTime = new Time(); // yyyy-MM-dd HH:mm
        Time classTime = new Time();
        classTime.set(liveTime * 1000);
        nowTime.setToNow();
        int[] nowTimeInt = new int[5];
        int[] msgTimeInt = new int[5];
        nowTimeInt[0] = nowTime.year;
        msgTimeInt[0] = classTime.year;
        nowTimeInt[1] = nowTime.month;
        msgTimeInt[1] = classTime.month;
        nowTimeInt[2] = nowTime.monthDay;
        msgTimeInt[2] = classTime.monthDay;
        nowTimeInt[3] = nowTime.hour;
        msgTimeInt[3] = classTime.hour;
        String backString = "";
        //一个小时
        if (nowTimeInt[0] == msgTimeInt[0] && nowTimeInt[1] == msgTimeInt[1]
                && nowTimeInt[2] == msgTimeInt[2] && (liveTime - nowTimestamp) <= 60 * 60) {
            double nearMinute = Math
                    .floor((liveTime - nowTimestamp) / 60);
            if (nearMinute <= 0) {
                backString = "即将开始";
            } else {
                backString = (int) nearMinute + "分钟后";
            }
            return backString;
        }
        //今天
        if (nowTimeInt[0] == msgTimeInt[0] && nowTimeInt[1] == msgTimeInt[1]
                && nowTimeInt[2] == msgTimeInt[2]) {
            backString = "今天" + parseTimeFormat(liveTime, "HH:mm");
            return backString;
        }
        //明天
        if (nowTimeInt[0] == msgTimeInt[0] && nowTimeInt[1] == msgTimeInt[1]
                && msgTimeInt[2] - nowTimeInt[2] == 1) {
            backString = "明天" + parseTimeFormat(liveTime, "HH:mm");
            return backString;
        }

        return timeFormatMD(liveTime);
    }


    @SuppressLint("SimpleDateFormat")
    public static String parseTimeFormat(long seconds, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);// "yyyy-MM-dd HH:mm"
        Date d = new Date(seconds * 1000);
        String currenttime = df.format(d);
        return currenttime;
    }

    // 获得当天0点时间
    public static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    // 获得当天24点时间
    public static long getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * 获取距离当前时间的格式，如：5分钟前等
     *
     * @param timeMills 毫秒！！！
     * @return 格式化的时间
     */
    public static String getListTime(long timeMills) {
        long now = System.currentTimeMillis();
        long msg = timeMills;

        Calendar nowCalendar = Calendar.getInstance();

        if (msgCalendar == null) {
            msgCalendar = Calendar.getInstance();
        }

        msgCalendar.setTimeInMillis(timeMills);

        long calcMills = now - msg;

        long calSeconds = calcMills / 1000;

        if (calSeconds < 60) {
            return JUST_NOW;
        }

        long calMins = calSeconds / 60;

        if (calMins < 60) {

            return new StringBuilder().append(calMins).append(MIN).toString();
        }

        long calHours = calMins / 60;

        if (calHours < 24 && isSameDay(nowCalendar, msgCalendar)) {
            if (dayFormat == null) {
                dayFormat = new SimpleDateFormat("HH:mm");
            }

            String result = dayFormat.format(msgCalendar.getTime());
            return new StringBuilder().append(TODAY).append(" ").append(result).toString();
        }

        long calDay = calHours / 24;

        if (calDay < 31) {
            if (isYesterDay(nowCalendar, msgCalendar)) {
                if (dayFormat == null) {
                    dayFormat = new SimpleDateFormat("HH:mm");
                }

                String result = dayFormat.format(msgCalendar.getTime());
                return new StringBuilder(YESTER_DAY).append(" ").append(result).toString();
            } else if (isTheDayBeforeYesterDay(nowCalendar, msgCalendar)) {
                if (dayFormat == null) {
                    dayFormat = new SimpleDateFormat("HH:mm");
                }

                String result = dayFormat.format(msgCalendar.getTime());
                return new StringBuilder(THE_DAY_BEFORE_YESTER_DAY).append(" ").append(result)
                        .toString();
            } else {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(DATE_FORMAT);
                }
                return dateFormat.format(msgCalendar.getTime());
            }
        }

        long calMonth = calDay / 31;

        if (calMonth < 12 && isSameYear(nowCalendar, msgCalendar)) {
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(DATE_FORMAT);
            }
            return dateFormat.format(msgCalendar.getTime());
        }
        if (yearFormat == null) {
            yearFormat = new SimpleDateFormat(YEAR_FORMAT);
        }
        return yearFormat.format(msgCalendar.getTime());
    }

    /**
     * 格式化时间
     * 先区分为今天，明天；
     * 非今天明天显示为具体时间 例如 11月20 19:00
     * @param startTimeSeconds 直播开始时间
     * @return 格式后时间
     */
    public static String formatLiveTime(long startTimeSeconds) {
        Calendar calendar = Calendar.getInstance();
        Calendar currentCalendar = Calendar.getInstance();

        //设置每周第一天为周一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        currentCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        try {
            calendar.setTime(new Date(startTimeSeconds * 1000));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        int liveWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int currentWeekOfYear = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        if (liveWeekOfYear != currentWeekOfYear) {//不属于本周,按照时间显示
            return TimeUtil.parseTimeFormat(startTimeSeconds, "MM月dd HH:mm");
        } else {//本周时间
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            int currentWeek = currentCalendar.get(Calendar.DAY_OF_WEEK);

            StringBuilder sd = new StringBuilder();
            if (week == currentWeek || week == (currentWeek + 1) % 7) {//今天或者明天
                sd.append(week == currentWeek ? "今天" : "明天");
                String hour_minute = TimeUtil.parseTimeFormat(startTimeSeconds, "HH:mm");
                sd.append(hour_minute);
                return sd.toString();
            } else {
                return TimeUtil.parseTimeFormat(startTimeSeconds, "MM月dd HH:mm");
            }
        }
    }

    private static boolean isSameHalfDay(Calendar now, Calendar msg) {
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int msgHOur = msg.get(Calendar.HOUR_OF_DAY);

        if (nowHour <= 12 & msgHOur <= 12) {
            return true;
        } else if (nowHour >= 12 & msgHOur >= 12) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSameDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return nowDay == msgDay;
    }

    private static boolean isYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);
        return (nowDay - msgDay) == 1;
    }

    private static boolean isTheDayBeforeYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);
        return (nowDay - msgDay) == 2;
    }

    private static boolean isSameYear(Calendar now, Calendar msg) {
        int nowYear = now.get(Calendar.YEAR);
        int msgYear = msg.get(Calendar.YEAR);
        return nowYear == msgYear;
    }

    public static boolean isSameDay(long timeMills) {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTimeInMillis(timeMills);
        return isSameDay(Calendar.getInstance(), newCalendar);
    }
}
