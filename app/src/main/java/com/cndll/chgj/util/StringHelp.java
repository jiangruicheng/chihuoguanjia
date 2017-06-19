package com.cndll.chgj.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiang_ruicheng on 17/5/1.
 */

public class StringHelp {
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();

    }

    public static boolean isFloat(String num) {
        Pattern pattern = Pattern.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    public static String round(String s) {
        float a = 0;
        String b = "";
        if (isFloat(s)) {
            a = Float.valueOf(s);
            a = Math.round(a);
            b = a + "";
        }
        return b;
    }

    public static String float2Int(String s) {
        float a;
        int c;
        String b = "";
        if (isFloat(s)) {
            a = Float.valueOf(s);
            if (a == 0) {
                b = "0";
            }
            if (a % ((int) a) == 0) {
                c = (int) a;
                b = c + "";
            } else {
                b = a + "";
            }
        }
        return b;
    }
}
