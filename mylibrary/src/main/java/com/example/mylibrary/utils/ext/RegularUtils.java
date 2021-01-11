package com.example.mylibrary.utils.ext;

import java.util.regex.Pattern;

/**
 * Created by zhangjia on 2016/5/31.
 * 一些正则表达式判断
 */
public class RegularUtils {
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String REGEX_URL = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
    /**
     * 是否是合法的昵称输入
     */
    public static final String REGEX_NAME="^([a-z0-9A-Z\\u4E00-\\u9FFF]+[_]*){0,30}$";
    /**
     * 是否是合法的密保问题输入
     */
    public static final String REGEX_QUESTION="^([a-z0-9A-Z\\u4E00-\\u9FFF]+){0,15}$";
    /**
     * 校验邮箱
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 是否是合法的昵称
     * @param name
     * @return
     */
    public static boolean isRightName(String name){
        return Pattern.matches(REGEX_NAME, name);
    }

    /**
     * 是否是合法的密保问题
     * @param question
     * @return
     */
    public static boolean isRightEncryptedQuestion(String question){
        return Pattern.matches(REGEX_QUESTION, question);
    }
    public static boolean isRightUrl(String url){
        return Pattern.matches(REGEX_URL, url);
    }
}
