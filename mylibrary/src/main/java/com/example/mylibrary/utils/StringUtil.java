package com.example.mylibrary.utils;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2016/10/26.
 */
public class StringUtil {
    private static final int PHONE_NUMBER = 1;
    private static final int PASSWORD = 2;
    private static final int NICK_NAME = 3;


    /**
     * 说明：移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186、166
     * 电信：133、153、180、189
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     * 验证号码 手机号 固话均可
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        String expression = "((^(13|15|18|17|16|19|14)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        return isCheck(phoneNumber, expression);
    }

    public static boolean isPassWord(String passWord) {
        //        String expression = "^w+$";
        //        String expression = "^(?![0-9]+$)(?![a-zA-Z_]+$)[0-9A-Za-z_]{6,14}$";
        String expression = "^[0-9A-Za-z_]{6,14}$";
        return isCheck(passWord, expression);
    }

    public static boolean isNickName(String nickName) {
        String expression = "[0-9A-Za-z_]{6,12}";
        return isCheck(nickName, expression);
    }

    /**
     * 校验字符串是否符合正则规则
     *
     * @param text
     * @param rex
     * @return
     */
    public static boolean isCheck(String text, String rex) {
        boolean isValid = false;
        CharSequence inputStr = text;
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 校验手机号格式
     *
     * @param text
     * @param nullText  数据为空的提示
     * @param errorText 数据错误的提示
     * @return
     */
    public static boolean checkPhoneNumber(String text, String nullText, String errorText) {
        return checkString(text, nullText, errorText, PHONE_NUMBER);

    }

    public static boolean checkNickName(String text, String nullText, String errorText) {
        return checkString(text, nullText, errorText, NICK_NAME);
    }

    /**
     * 校验密码格式
     *
     * @param text
     * @param nullText  数据为空的提示
     * @param errorText 数据错误的提示
     * @return
     */
    public static boolean checkPassWord(String text, String nullText, String errorText) {
        return checkString(text, nullText, errorText, PASSWORD);
    }

    private static boolean checkString(String text, String nullText, String errorText, int types) {
        if (EmptyUtil.isEmpty(text)) {
            return false;
        }
        switch (types) {
            case PHONE_NUMBER:
                if (!isPhoneNumber(text)) {
                    return false;
                }
                break;
            case PASSWORD:
                if (!isPassWord(text)) {
                    return false;
                }
                break;
            case NICK_NAME:
                if (!isNickName(text)) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * textview 部分文字加颜色
     *
     * @param textview 目标textview
     * @param allStr   全部String
     * @param colorStr 要加色的String
     */
    public static void partTextViewColor(TextView textview, String allStr,
                                         String colorStr, int color) {

        if (!allStr.contains(colorStr) || allStr.indexOf(colorStr) == -1) {
            return;
        }

        // 起始位置
        int startIndex = allStr.indexOf(colorStr);
        // 终止位置
        int endIndex = startIndex + colorStr.length();
        // 文字编排
        SpannableStringBuilder builder = new SpannableStringBuilder(allStr);

        // 指定位置上色
        builder.setSpan(new ForegroundColorSpan(color), startIndex, endIndex,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        textview.setMovementMethod(LinkMovementMethod.getInstance());
        textview.setText(builder);

    }

    /**
     * textview 部分文字加颜色
     *
     * @param textview 目标textview
     * @param allStr   全部String
     * @param colorStr 要加色的String
     */
    public static void partTextViewHintColor(TextView textview, String allStr,
                                             String colorStr, int color) {

        if (!allStr.contains(colorStr) || allStr.indexOf(colorStr) == -1) {
            return;
        }

        // 起始位置
        int startIndex = allStr.indexOf(colorStr);
        // 终止位置
        int endIndex = startIndex + colorStr.length();
        // 文字编排
        SpannableStringBuilder builder = new SpannableStringBuilder(allStr);

        // 指定位置上色
        builder.setSpan(new ForegroundColorSpan(color), startIndex, endIndex,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        textview.setMovementMethod(LinkMovementMethod.getInstance());
        textview.setHint(builder);

    }

    /**
     * textview 多部分文字加颜色
     *
     * @param textview     目标textview
     * @param allStr       全部String
     * @param listColorStr 要加色的字符串集合
     */
    public static void partTextViewColor(TextView textview, String allStr,
                                         List<String> listColorStr, int color) {

        /*if (!allStr.contains(colorStr) || allStr.indexOf(colorStr) == -1) {
            return;
        }*/

        // 文字编排
        SpannableStringBuilder builder = new SpannableStringBuilder(allStr);
        for (int i = 0; i < listColorStr.size(); i++) {
            String colorStr = listColorStr.get(i);
            // 起始位置
            int startIndex = allStr.indexOf(colorStr);
            // 终止位置
            int endIndex = startIndex + colorStr.length();

            // 指定位置上色
            builder.setSpan(new ForegroundColorSpan(color), startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        textview.setText(builder);

    }

    /**
     * textview 分享红包文字显示
     *
     * @param textview 目标textview
     * @param allStr   全部String
     * @param colorStr 要加色的String
     */
    public static void partTextRedPacket(TextView textview, String allStr,
                                         String colorStr, int color) {

        if (!allStr.contains(colorStr) || allStr.indexOf(colorStr) == -1) {
            return;
        }

        // 起始位置
        int startIndex = allStr.indexOf(colorStr);
        // 终止位置
        int endIndex = startIndex + colorStr.length();
        // 文字编排
        SpannableStringBuilder builder = new SpannableStringBuilder(allStr);

        // 指定位置上色
        builder.setSpan(new ForegroundColorSpan(color), startIndex, endIndex,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(36);
        //        builder.setSpan(absoluteSizeSpan, startIndex, endIndex - 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        textview.setText(builder);

    }


    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     * <p/>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 1.5.2
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Compares two Strings, returning <code>true</code> if they are equal.
     * </p>
     * <p/>
     * <p>
     * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
     * The comparison is case sensitive.
     * </p>
     *
     * @param str1 the first string
     * @param str2 the second string
     * @return <code>true</code> if the Strings are equal, case sensitive, or both <code>null</code>
     * @see String#equals(Object)
     */
    public static boolean equals(String str1, String str2) {
        return (str1 == null ? str2 == null : str1.equals(str2));
    }

    /**
     * 手机号加空格
     *
     * @param mEditText
     */
    public static void phoneNumAddSpace(final EditText mEditText) {
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(
                23)});
        mEditText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3
                        || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 3 || index == 8)) {
                            buffer.insert(index, " ");
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }
                    mEditText.setText(str);
                    Editable etable = mEditText.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    public static String bankCardNumAddApace(String num) {
        StringBuffer buffer = new StringBuffer();
        char[] charArray = num.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if ((i == 4 || i == 8 || i == 12 || i == 16)) {
                buffer.append(" " + charArray[i]);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    public static String getVideoTime(String time) {
        if (time.length() > 8) {
            return time.substring(4, 8);
        }
        return time;
    }

    /**
     * 给手机号加*****
     *
     * @param phone
     * @return
     */
    public static String addPhoneStar(String phone) {
        String substring = phone.substring(0, 3);
        String substring1 = phone.substring(9, 11);
        return substring + "******" + substring1;
    }
}
