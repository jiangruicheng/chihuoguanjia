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
}
