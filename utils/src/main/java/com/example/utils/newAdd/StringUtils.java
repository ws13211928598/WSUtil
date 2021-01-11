package com.example.utils.newAdd;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;


/**
 * Author:Jason
 * <p>
 * Date:2016/10/8
 */

public class StringUtils {
    /**
     * 字数限制时候用
     * 计算分享内容的字数，一个汉字=两个英文字母，一个中文标点=两个英文标点 注
     * 意：该函数的不适用于对单个字符进行计算，因为单个字符四舍五入后都是1
     *
     * @param c
     * @return
     */
    public static int textLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return (int) Math.round(len);
    }

    /**
     * 处理字符串中的转义
     */
    public static String translation(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        if (string.contains("&lt;")) {
            string = string.replaceAll("&lt;", "<");
        }
        if (string.contains("&gt;")) {
            string = string.replaceAll("&gt;", ">");
        }
        if (string.contains("&#039;")) {
            string = string.replaceAll("&#039;", "'");
        }
        if (string.contains("&quot;")) {
            string = string.replaceAll("&quot;", "\"");
        }
        if (string.contains("&amp;")) {
            string = string.replaceAll("&amp;", "&");
        }
        if (string.contains("&nbsp;")){
            string = string.replaceAll("&nbsp;", " ");
        }
        return string;
    }

    /**
     * 去掉小数点后无用的0
     *
     * @param money 钱数、或者百分比（考试得分）
     * @return 去除小数点后无用0的字符串
     */
    public static String getRealMoney(String money) {
        if (money == null)
            return "";
        if (money.contains(".")) {
            int index = money.indexOf(".");
            if (Integer.valueOf(money.substring(index + 1, money.length())) == 0) {
                money = money.substring(0, index);
            }
        }
        return money;
    }

    /**
     * 改变图片url
     *
     * @param url
     * @return
     */
    public static String getBigImageUrl(String url) {
        if (TextUtils.isEmpty(url)) return "";
        //gif图 暂不做处理
        if (!url.endsWith(".gif") && url.contains("_0_0_")) {
            String[] strings = url.split("_0_0_");
            int length = strings.length;
            if (length > 1) {
                int index = url.lastIndexOf('.');
                String end = url.substring(index);
                return url.replace(strings[1], "320_180" + end);
            }
        }
        return url;
    }

    /**
     * 人数计算，如果人数超过一万人转换
     *
     * @param number
     * @return
     */
    public static String getPeople(String number) {
        if (number.length() < 5) return number;
        int studentgnum = Integer.parseInt(number);
        return String.format("%.2f万", studentgnum / 10000.0f);
    }

    /**
     * 对评分进行向上取整操作，如4.1为4.5, 4.6为5.0
     *
     * @param realRank 真实的评分
     * @return 取整之后的评分
     */
    public static float getRoundRank(String realRank) {
        if (TextUtils.isEmpty(realRank))
            return 5.0f;

        float newRank = 0.0f;
        float rank = Float.parseFloat(realRank);
        float floorRank = (float) Math.floor(rank);
        newRank = floorRank + 0.5f >= rank ? (rank == floorRank ? rank : floorRank + 0.5f) : floorRank + 1;
        return newRank;
    }

    /**
     * 单独设置内部字体颜色
     * @param text
     * @param keyworld
     * @return
     */
    public static SpannableStringBuilder getSpannableTextColor(String text, String keyworld, int color){
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(text);
        if(text.contains(keyworld)){
            int spanStartIndex=text.indexOf(keyworld);
            int spacEndIndex=spanStartIndex+keyworld.length();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(color),spanStartIndex,spacEndIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return spannableStringBuilder;
    }

    /**
     * 改变字体前半部分的颜色和大小
     */
    public static SpannableString getPraxisSpannable(Context context, String content, String index) {
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(
                new ForegroundColorSpan(Color.RED),
                0, index.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(
                new RelativeSizeSpan(3.0f),
                0, index.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 改变字体前半部分的颜色和大小
     */
    public static SpannableString getPraxisSpannable2(Context context, String content, String index) {
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(
                new ForegroundColorSpan(Color.RED),
                0, index.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(
                new AbsoluteSizeSpan(22,true),
                0, index.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
