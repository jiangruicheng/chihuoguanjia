package com.cndll.chgj.mvp.mode.bean.info;

/**
 * Created by kongqing on 2017/4/5.
 */

public class AppMode {


    private AppMode() {

    }

    private static class INIT {
        private static final AppMode APP_MODE = new AppMode();
    }

    public static AppMode getInstance() {
        return INIT.APP_MODE;
    }
}
