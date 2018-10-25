package com.allen_anker.regex;

import java.util.Arrays;

public class RegexDemo {

    public static void main(String[] args) {
//        System.out.println(checkQQ("19883"));
        System.out.println(checkDemo2("21112112"));
    }

    public static boolean checkQQ(String qq) {
        boolean flag = true;
        String pattern = "[1-9]\\d{4,10}";
        flag = qq.matches(pattern);
        return flag;
    }

    public static boolean checkDemo2(String s) {
        boolean flag = false;
        String pattern = "(.)\\1+";
        flag = s.matches(pattern);
        System.out.println(Arrays.toString(s.split(pattern)));
        return flag;
    }
}
