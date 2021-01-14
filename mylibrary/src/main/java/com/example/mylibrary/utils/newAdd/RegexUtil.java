package com.example.mylibrary.utils.newAdd;

import android.app.Activity;


import com.example.mylibrary.utils.ext.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 任小龙 on 2020/3/20.
 */
public class RegexUtil {
    public static boolean isPhone(String name) {
        Pattern p = Pattern.compile("^1[3,4,5,6,7,8,9]\\d{9}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }
    public static boolean checkPassword(String password) {
        //字母+数字。最少6位的正则表达式
        Pattern p = Pattern.compile("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}");
        Matcher m = p.matcher(password);
        boolean isPassword = m.matches();
        return isPassword;
    }
    public static boolean checkTwicePwd(Activity activity, String password, String username,
                                       String oldPassword, String surePwd) {
        //password = et_password.getText().toString();
        String reg = "^[a-zA-Z0-9_]{6,18}$";
        String reg1 = "^([0-9]{6,18})$";
        String reg2 = "^([a-zA-Z]{6,18})$";
        if(password.equals(oldPassword)){
            ToastUtils.show(activity,"新密码和当前密码不能相同");
            return false;
        }
        if(!password.equals(surePwd)){
            ToastUtils.show(activity,"两次密码不一致");
            return false;
        }
        if (password.matches(reg)) {
            if (password.matches(reg1)) {

            }
            if (password.matches(reg2)) {
                ToastUtils.show(activity,"密码不能全字母");
                return false;
            }
            if (password.startsWith("000000") || password.startsWith("111111")
                    || password.startsWith("222222")
                    || password.startsWith("333333")
                    || password.startsWith("555555")
                    || password.startsWith("666666")
                    || password.startsWith("777777")
                    || password.startsWith("888888")
                    || password.startsWith("999999")) {
                ToastUtils.show(activity,"密码前6位不能用相同的数字");
                return false;
            }
            if (password.equals(username)) {
                ToastUtils.show(activity,"密码不能与用户名相同");
                return false;
            }
        } else {
            ToastUtils.show(activity,"密码格式不正确");
            return false;
        }
        return true;
    }
}
