package com.example.ckglixt.utils;

import java.util.Random;

/**
 * <p>权限工具类</p>
 * <p>创建日期：2018-03-25</p>
 *
 * @author scout
 */
public class AuthUtils {
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";

    /**
     * 获取当前登录用户id
     * @return 用户id
     */
    public static String getCurrentUserId(){
        return "1";
    }
    public static String getCurrentUmenuId(){
        return "1";
    }
    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }
}
