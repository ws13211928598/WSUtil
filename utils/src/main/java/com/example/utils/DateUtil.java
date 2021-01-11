package com.example.utils;

import android.util.Log;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;


public class DateUtil {

    // 相关日期格式
    public static SimpleDateFormat formatModel1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatModel2 = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public static SimpleDateFormat formatModel3 = new SimpleDateFormat(
            "yyyy-MM-dd");
    private static SimpleDateFormat formatModel4 = new SimpleDateFormat(
            "yyyyMMdd");
    public static SimpleDateFormat formatModel5 = new SimpleDateFormat(
            "yyyy年MM月dd日");
    private static SimpleDateFormat formatModel6 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    private static SimpleDateFormat formatModel7 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    private static SimpleDateFormat formatModel8 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss:SSS");

    public static SimpleDateFormat formatModel9 = new SimpleDateFormat("M月d日");
    private static SimpleDateFormat formatModel10 = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    public static SimpleDateFormat formatModel11 = new SimpleDateFormat(
            "yyyy.MM.dd");
    public static SimpleDateFormat formatModel12 = new SimpleDateFormat(
            "MM/dd HH:mm");
    public static SimpleDateFormat formatModel13 = new SimpleDateFormat(
            "HH:mm");
    public static SimpleDateFormat formatModelToken = new SimpleDateFormat(
            "yyyyMMddHH");


    /**
     * 获取当前时间格式为(yyyyMMddHH)的日期字符串
     *
     * @return
     */
    public static String getyyyyMMddHH() {
        String dateStr = "";
        try {
            Date date = new Date();
            dateStr = formatModelToken.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取当前时间格式为(yyyymmdd)的日期字符串
     *
     * @return
     */
    public static String getYyyyMmDd() {
        String dateStr = "";
        try {
            Date date = new Date();
            dateStr = formatModel4.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取当前时间格式为(yyyy-MM-dd HH:mm:ss)的日期字符串
     *
     * @return
     */
    public static String getYyyyMmDdHmss() {
        String dateStr = "";
        try {
            Date date = new Date();
            dateStr = formatModel1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取当前时间格式为(yyyyMMddHHmmss)的日期字符串
     *
     * @return
     */
    public static String getYyyyMmDdHms() {
        String dateStr = "";
        try {
            Date date = new Date();
            dateStr = formatModel2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取当前时间格式为(yyyy.MM.dd HH:mm)的日期字符串
     *
     * @return
     */
    public static String getYyyyMmDdHm(Date date) {
        String dateStr = "";
        try {
            dateStr = formatModel6.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取当前时间格式为(yyyy.MM.dd)的日期字符串
     *
     * @return
     */
    public static String getYyyyMmDd(Date date) {
        String dateStr = "";
        try {
            dateStr = formatModel3.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取时间格式为（yyyy-MM-dd HH:mm:ss:SSS）
     *
     * @param date
     * @return
     */
    public static String getMMDDHHMM(Date date) {
        String dateStr = "";
        try {
            dateStr = formatModel10.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static long getLongTime(String time) {
        try {
            return formatModel6.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getLongTime(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取时间格式为（yyyy-MM-dd HH:mm:ss:SSS）
     *
     * @param date
     * @return
     */
    public static String getDetailStr(Date date) {
        String dateStr = "";
        try {
            dateStr = formatModel8.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 字符串转换时间
     *
     * @param dateStr 时间字符串
     * @param bool    （true:时间加时分秒，false:时间不加时分秒）
     * @return 时间
     */
    public static Date strFormatDate(String dateStr, boolean bool) {
        Date date = null;
        try {
            if (bool) {
                date = formatModel1.parse(dateStr);
            } else {
                date = formatModel3.parse(dateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strFormatDate(String dateStr) {
        Date date = null;
        try {
            date = formatModel10.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间戳转换成固定格式时间 HH:mm
     *
     * @param timestamp
     * @return
     */
    public static String getHHMM(Long timestamp) {
        return formatModel13.format(new Date(timestamp));
    }

    /**
     * 将时间戳转换为日期格式
     *
     * @param dateStr 时间戳
     * @return
     */
    public static String strFormatTimeStrYyyyMmDd(String dateStr) {
        String timeStr = null;
        try {
            timeStr = formatModel1.format(new Date(Long.parseLong(dateStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStr;
    }

    /**
     * 将时间戳转换为日期格式
     *
     * @param dateStr 时间戳
     * @return
     */
    public static String strFormatTimeStr(String dateStr) {
        String timeStr = null;
        try {
            timeStr = formatModel7.format(new Date(Long.parseLong(dateStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStr;
    }

    /**
     * 两个时间差
     *
     * @return
     */
    public static Long dateDiffOfDay(Date firstDate, Date secondDate) {
        Long lastTime = secondDate.getTime() - firstDate.getTime();
        Long lastDays = lastTime / (24 * 60 * 60 * 1000);
        return lastDays;
    }

    /**
     * 相差的小时数
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static Long dateDiffOfHour(Date firstDate, Date secondDate) {
        Long lastTime = secondDate.getTime() - firstDate.getTime();
        Long lastHours = lastTime / (60 * 60 * 1000);
        return lastHours;
    }

    public static Date dateAddDay(Date date, Integer days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }

    /**
     * @param currentTimeMillis 起始时间戳
     * @param days              累加天数
     * @return 返回 1月2号 去掉两位数 前面的“0”
     */
    public static String dateAddDay(String currentTimeMillis, int days,
                                    SimpleDateFormat simpFormat) {
        Date dateAddDay = com.example.utils.DateUtil.dateAddDay(
                new Date(Long.parseLong(currentTimeMillis)), days);
        return simpFormat.format(dateAddDay);

    }

    /**
     * @param currentTimeMillis 起始时间戳
     * @param days              累加天数
     * @return 返回 1月2号 去掉两位数 前面的“0”
     */
    public static String dateAddDay(String currentTimeMillis, int days) {
        Date dateAddDay = com.example.utils.DateUtil.dateAddDay(
                new Date(Long.parseLong(currentTimeMillis)), days);
        return formatModel9.format(dateAddDay);

    }

    /**
     * 输出日期格式：yyyy-MM-dd 的字符串格式。
     *
     * @param date Date
     * @return String
     */
    public static String formatDate(Date date) {
        return formatModel3.format(date);
    }

    public static String formatDate(Date date,
                                    SimpleDateFormat formatModel) {
        return formatModel.format(date);
    }

    /**
     * 计算剩余天数
     *
     * @param days    天数
     * @param created 创建日期
     * @return
     */
    public static String getRemainingTime(Integer days, Date created) {
        Calendar c = Calendar.getInstance();
        c.setTime(created);
        c.add(Calendar.DATE, days);
        Date end = new Date();
        long between = (c.getTimeInMillis() - end.getTime()) / 1000;
        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        // long minute1=between%3600/60;
        // long second1=between%60/60;
        return "" + day1 + "天" + hour1 + "小时";
    }

    /**
     * 根据类型格式化日期时间
     *
     * @param type 类型
     * @return 格式化完成后的日期时间
     */
    public static String getFormatDateType(String type) {

        SimpleDateFormat df = new SimpleDateFormat(type);

        return df.format(new Date());

    }

    public static String getFormatTimestampByType(Timestamp date, String type) {

        SimpleDateFormat df = new SimpleDateFormat(type);

        return df.format(date);

    }

    /**
     * 下面的两个成员变量分别是日期分隔符字符串和字符串分隔器，专门用来解析字符串格式的日期
     * 程序中主要的日期分隔符为"-"和"/"，且日期序列为“年/月/日”型，其内容缺一不可 例如:09/02/02或2009-02-02
     */
    public static final String DATE_SEPARATOR = "-/: ";
    public static StringTokenizer sToken;

    // 1.1格式转换

    /**
     * 将日期变为字符串格式 yyyy-MM-dd
     **/
    public static String format(GregorianCalendar pCal) {
        return formatModel3.format(pCal.getTime());
    }

    /**
     * 将日期变为字符串格式 yyyy-MM-dd
     **/
    public static String format(Date pDate) {
        return formatModel3.format(pDate);
    }

    /**
     * 将日期变为字符串格式 yyyy-MM-dd HH:mm:ss
     **/
    public static String fullFormat(Date pDate) {
        return formatModel1.format(pDate);
    }

    /**
     * 将字符串格式的日期转换为Calender
     **/
    public static GregorianCalendar parse2Cal(String pDateStr) {
        sToken = new StringTokenizer(pDateStr, DATE_SEPARATOR);
        int vYear = Integer.parseInt(sToken.nextToken());
        // GregorianCalendar的月份是从0开始算起的，变态！！
        int vMonth = Integer.parseInt(sToken.nextToken()) - 1;
        int vDayOfMonth = Integer.parseInt(sToken.nextToken());
        return new GregorianCalendar(vYear, vMonth, vDayOfMonth);
    }

    /**
     * 将字符串类型的日期转换成Date(yyyy-MM-dd)
     **/
    public static Date parse2Date(String pDate) {
        try {
            if (EmptyUtil.isEmpty(pDate)) {
                return null;
            }
            return formatModel3.parse(pDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 将字符串类型的日期转换成Date (yyyy-MM-dd HH:mm:ss)
     **/
    public static Date parse2FullDate(String pDate) {
        try {
            return formatModel1.parse(pDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date parse(String pDate, String formatType) {
        try {
            if (EmptyUtil.isEmpty(formatType)) {
                return null;
            }
            SimpleDateFormat fmt = new SimpleDateFormat(formatType);
            return fmt.parse(pDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * @throws
     * @Title:toChineseDate
     * @Description:获取中文日期格式
     * @param:@param pDate
     * @param:@return
     * @return:String yyyy年MM月dd日
     * @Create: 2013-7-3 下午5:01:14
     * @Author : Andy Tang
     */
    public static String toChineseDate(Date date) {
        try {
            return formatModel5.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    // 1.2.取得两个日期之间所差天数的方法

    /**
     * 返回未来的某一天和今天所差的日期数 注意，这里要clone一个新的日期以免对原始日期类造成的修改。
     * 而在daysBetween(GregorianCalendarpFormer,GregorianCalendarpLatter)就
     * 直接处理而不进行clone动作，因为这里已经做了:)
     **/
    public static int daysBetween(GregorianCalendar pFurtherDay) {
        GregorianCalendar vToday = new GregorianCalendar();
        GregorianCalendar vFurtherDay = (GregorianCalendar) pFurtherDay.clone();
        return daysBetween(vToday, vFurtherDay);
    }

    /**
     * 上面函数的String版本
     **/
    public static int daysBetween(String pFurtherDayStr) {
        GregorianCalendar vFurtherDay = com.example.utils.DateUtil.parse2Cal(pFurtherDayStr);
        GregorianCalendar vToday = new GregorianCalendar();
        return daysBetween(vToday, vFurtherDay);
    }

    /**
     * 返回较晚的时间(latter)与较早的时间(former)所差的天数
     **/
    public static int daysBetween(String pFormerStr, String pLatterStr) {
        GregorianCalendar pFormer = com.example.utils.DateUtil.parse2Cal(pFormerStr);
        GregorianCalendar pLatter = com.example.utils.DateUtil.parse2Cal(pLatterStr);
        return daysBetween(pFormer, pLatter);
    }

    /**
     * 返回较晚的时间(latter)与较早的时间(former)所差的天数
     **/
    public static int daysBetween(GregorianCalendar pFormer,
                                  GregorianCalendar pLatter) {
        GregorianCalendar vFormer = pFormer, vLatter = pLatter;
        boolean vPositive = true;
        if (pFormer.before(pLatter)) {
            vFormer = pFormer;
            vLatter = pLatter;
        } else {
            vFormer = pLatter;
            vLatter = pFormer;
            vPositive = false;
        }

        vFormer.set(Calendar.MILLISECOND, 0);
        vFormer.set(Calendar.SECOND, 0);
        vFormer.set(Calendar.MINUTE, 0);
        vFormer.set(Calendar.HOUR_OF_DAY, 0);
        vLatter.set(Calendar.MILLISECOND, 0);
        vLatter.set(Calendar.SECOND, 0);
        vLatter.set(Calendar.MINUTE, 0);
        vLatter.set(Calendar.HOUR_OF_DAY, 0);

        int vCounter = 0;
        while (vFormer.before(vLatter)) {
            vFormer.add(Calendar.DATE, 1);
            vCounter++;
        }
        if (vPositive) {
            return vCounter;
        } else {
            return -vCounter;
        }
    }

    // 1.3.两个日期的月份差

    /**
     * 本月和未来一个月的月份差
     **/
    public static int monthsBetween(GregorianCalendar pFurtherMonth) {
        GregorianCalendar vToday = new GregorianCalendar();
        GregorianCalendar vFurtherMonth = (GregorianCalendar) pFurtherMonth
                .clone();
        return monthsBetween(vToday, vFurtherMonth);
    }

    /**
     * 给定月分和本月的月份差
     **/
    public static int monthsBetween(String pFurtherMonth) {
        GregorianCalendar vToday = new GregorianCalendar();
        GregorianCalendar vFurtherMonth = com.example.utils.DateUtil.parse2Cal(pFurtherMonth);
        return monthsBetween(vToday, vFurtherMonth);
    }

    /**
     * 给定两个时间相差的月份,String版
     **/
    public static int monthsBetween(String pFormerStr, String pLatterStr) {
        GregorianCalendar vFormer = com.example.utils.DateUtil.parse2Cal(pFormerStr);
        GregorianCalendar vLatter = com.example.utils.DateUtil.parse2Cal(pLatterStr);
        return monthsBetween(vFormer, vLatter);
    }

    public static int monthsBetween(GregorianCalendar pFormer,
                                    GregorianCalendar pLatter) {
        GregorianCalendar vFormer = pFormer, vLatter = pLatter;
        boolean vPositive = true;
        if (pFormer.before(pLatter)) {
            vFormer = pFormer;
            vLatter = pLatter;
        } else {
            vFormer = pLatter;
            vLatter = pFormer;
            vPositive = false;
        }
        int vCounter = 0;
        while (vFormer.get(Calendar.YEAR) != vLatter.get(Calendar.YEAR)
                || vFormer.get(Calendar.MONTH) != vLatter.get(Calendar.MONTH)) {
            vFormer.add(Calendar.MONTH, 1);
            vCounter++;
        }
        if (vPositive) {
            return vCounter;
        } else {
            return -vCounter;
        }
    }

    /**
     * 返回今天是本月的第几天
     **/
    public static int dayOfMonthOfToday() {
        GregorianCalendar vTodayCal = new GregorianCalendar();
        return vTodayCal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回本月是本年的第几个月
     **/
    public static int monthOfYear() {
        GregorianCalendar vTodayCal = new GregorianCalendar();
        return vTodayCal.get(Calendar.MONTH) + 1;
    }

    // 返回给定日期的月份
    public static String getMonth(String pFormattedDate) {
        StringTokenizer vSt = new StringTokenizer(pFormattedDate, "-");
        vSt.nextToken();// 跳过年份
        int val = Integer.parseInt(vSt.nextToken());
        return val + "";
    }

    /**
     * 获得从本月开始到本月+pZoneSize区间内的月数
     **/
    public static String[] monthList(int pZoneSize) {
        String[] vMonthList = new String[pZoneSize];
        GregorianCalendar vTodayCal = new GregorianCalendar();
        for (int i = 0; i < pZoneSize; i++) {
            vMonthList[i] = String.valueOf(vTodayCal.get(Calendar.MONTH) + 1);
            vTodayCal.roll(Calendar.MONTH, true);
        }
        return vMonthList;
    }

    /**
     * 仅显示小数点后两位的Formater
     **/
    public static DecimalFormat formatter = new DecimalFormat("####.##");

    /**
     * 将给定的数字变成小数点后两位的字符串
     **/
    public static String format(double pSrcVal) {
        return formatter.format(pSrcVal);
    }

    public static String parseTimeMini(String dateStr) {
        Date dd = null;
        try {
            dd = formatModel1.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dd == null) {
            return null;
        }
        return String.valueOf(dd.getTime());
    }

    /**
     * 将指定日期增量后得到一个新的日期
     *
     * @param type 增量类型，Calendar.DAY_OF_MONTH、Calendar.MONTH等
     * @param num  增量的数量值
     */
    public static Date add(Date date, int type, int num) {
        Calendar cla = Calendar.getInstance();
        cla.setTime(date);
        cla.add(type, num);
        return cla.getTime();
    }

    /**
     * 将指定日期增量后得到一个新的日期
     *
     * @param dataStr 当前时间yy-MM-dd
     *                //@param num     增量的数量值
     */
    public static String getAddDeta(String dataStr, int day) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int d = c.get(Calendar.DATE);
        c.set(Calendar.DATE, d + day);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }

    /**
     * 日期比较大小 返回 0 表示时间日期相同 1 表示日期1>日期2 -1 表示日期1<日期2
     */
    public static int CompareDate(String str1, String str2) {
        try {
            Date date1 = formatModel3.parse(str1);
            Date date2 = new Date();
            if (!EmptyUtil.isEmpty(str2)) {
                date2 = formatModel3.parse(str2);
            }
            int number = date1.compareTo(date2);
            return number;
        } catch (ParseException e) {

            e.printStackTrace();
            return -2;
        }
    }

    /**
     * 日期比较大小 返回 0 表示时间日期相同 1 表示日期1>日期2 -1 表示日期1<日期2
     */
    public static int CompareDate(Date date1, Date date2) {
        int number = date1.compareTo(date2);
        return number;
    }

    /**
     * @param current 当前的时间戳
     * @param value   要比较的时间戳
     * @return 两个时间戳相差的天数
     */
    public static long differValue(String current, String value) {
        Date date;
        Date mydate;
        long day = 0;
        try {
            date = formatModel3.parse(strFormatTimeStrYyyyMmDd(current));
            mydate = formatModel3.parse(strFormatTimeStrYyyyMmDd(value));
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;

    }

    public static long differValueday(String startTime, String endTime) {

        long differValue = 0;
        try {
            Date startTimeDate = formatModel3.parse(startTime);
            Date endTimeDate = formatModel3.parse(endTime);

            differValue = differValue(startTimeDate.getTime() + "",
                    endTimeDate.getTime() + "");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return differValue;

    }

    public static String convertTimeToFormat(String timeStamp) {
        Date date = null;
        try {
            date = formatModel1.parse(formatModel1.format(Long.valueOf(timeStamp)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        return convertTimeToFormat(date.getTime());
    }

    public static String convertTimeToFormatNew(String timeStamp) {
        Date date = null;
        try {
            date = formatModel1.parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertTimeToFormatNew(date.getTime());
    }

    public static String todayOrYesterday(String timeStamp) {
        Date date = null;
        try {
            date = formatModel1.parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayOrYesterday(date);
    }

    public static Long stringToLong(String timeStamp) {
        Date date = null;
        try {
            date = formatModel1.parse(timeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis();
        long time = (curTime - timeStamp) / (long) 1000;
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            //TODO 这里做了 *1000 的操作，如果出现bug,可以考虑修改
            return getHHMM(timeStamp * 1000);
            //return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
            //return todayOrYesterday(timeStamp);
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
            //return todayOrYesterday(timeStamp);
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
            //return todayOrYesterday(timeStamp);
        } else {
            return "刚刚";
        }
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormatNew(long timeStamp) {
        long curTime = System.currentTimeMillis();
        long time = (curTime - timeStamp) / (long) 1000;
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
            //            return todayOrYesterday(timeStamp);
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
            //            return todayOrYesterday(timeStamp);
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
            //            return todayOrYesterday(timeStamp);
        } else {
            return "刚刚";
        }
    }

    public static boolean compare(String timeStamp, String date) {
        Date date1 = null;
        Date date2 = null;
        long l = System.currentTimeMillis();
        Date curent = new Date(l);
        try {
            date1 = formatModel1.parse(timeStamp);
            date2 = formatModel1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date2.getDate() == date1.getDate()) {
            return false;
        } else if (date2.getDate() - curent.getDate() > 1) {
            return false;
        } else {
            return true;
        }
    }

    public static String todayOrYesterday(Date timeStamp) {
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        int day = date.getDate();
        int date1 = timeStamp.getDate();
        if (day == date1) {
            return "今天";
        } else if (day - date1 == 1) {
            return "昨天";
        } else {
            return "以前";
        }
    }

    public static void todayOrYesterday2(String timeStamp) {
        Date dateOld = null;
        Date dateTeday = null;
        try {
            dateOld = formatModel1.parse(timeStamp);
            long l = System.currentTimeMillis();
            dateTeday = new Date(l);
            Log.e("dateTeday", "dateTeday");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static int getJudgetoDay(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//得到当前的时间
            Date curDate = new Date(System.currentTimeMillis());
            String str = formatter.format(curDate);
            boolean flag = IsToday(time);
            if (flag) {
                //今天
                return 0;
            }
            boolean flag1 = IsYesterday(time);
            if (flag1) {
                //昨天
                return 1;
            }
            Log.d("Yesterday", "以前");
        } catch (ParseException e) {
            e.printStackTrace();
            return 2;
        }
        return 2;
    }

    public static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    public static boolean IsYesterday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，今天，昨天
     *
     * @param timeStamp
     * @return
     */
    public static String todayOrYesterday(long timeStamp) {
        long curTime = System.currentTimeMillis();
        long time = (curTime - timeStamp) / (long) 1000;
        if (time < 3600 * 24) {
            return "今天";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 2) {
            return "昨天";
        } else {
            return "以前";
        }


        //            if (time >= 3600 * 24 * 2 && time < 3600 * 24 * 3) {
        //            return "前天";
        //        } else {
        //            Date date = new Date(timeStamp);
        //            //return date.getMonth() + "-" + date.getDay();
        //            return time / 3600 / 24 + "天前";
        ////            return date.getMonth() + 1 + "/" + date.getDate();
        //        }

    }

    /**
     * 获取视频评论日期格式
     *
     * @param timeStamp
     * @return
     */
    public static String getVideoCommentDate(Long timeStamp) {
        if (timeStamp != null) {
            Date date = new Date(timeStamp);
            return formatModel12.format(date);
        }
        return "";
    }

    /**
     * @param time 一个long型的时间
     * @return 返回一个包含时间的数组，角标0是年，角标1是月，角标2是天
     */
    public static int[] getTimes(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH) + 1;//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR);//小时
        int[] times = {year, month, day, hour};
        return times;
    }

    /**
     * 获取指定月份的英文
     *
     * @param month 指定的月份
     * @param b     true就是英文的简写，否则就全写
     * @return 返回的英文月份
     */
    public static String getMonth(String month, boolean b) {
        try {
            String str;
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            SimpleDateFormat format2 = new SimpleDateFormat(str = b ? "MMM" : "MMMM", Locale.US);
            Date date = sdf.parse(month);
            return format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 1转成一，最大到12
     *
     * @param num 需要转的数字
     */
    public static String getNumFormatCh(int num) {
        switch (num) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            case 10:
                return "十";
            case 11:
                return "十一";
            case 12:
                return "十二";
        }
        return "";
    }
}
