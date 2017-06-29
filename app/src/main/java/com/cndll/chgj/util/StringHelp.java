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
        } else {
            b = s;
        }
        return b;
    }

    public static String float2Int(String s) {

        if (isFloat(s) || s.equals("0.0")) {
            String[] s1 =/*s.split("\\.") */s.indexOf(".") != -1 ? s.split("\\.") : new String[]{s.toString()};
            if (s1.length > 1)
                s = s1[1] == null ? s : (Float.valueOf(s1[1]) == 0 ? s1[0] : s);
            return s;
        } else {
            return s;
        }
    }
}
