package com.cndll.chgj.mvp.mode.bean.info;

/**
 * Created by kongqing on 2017/4/5.
 */

public class AppMode {
    public synchronized String getMid() {
        return mid;
    }

    public synchronized AppMode setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public synchronized String getToken() {
        return token;
    }

    public synchronized AppMode setToken(String token) {
        this.token = token;
        return this;
    }

    public synchronized String getUid() {
        return uid;
    }

    public synchronized AppMode setUid(String uid) {
        this.uid = uid;
        return this;
    }

    private String mid = "3";
    private String token;
    private String uid = "3";
    private boolean isBoss;

    public synchronized boolean isBoss() {
        return isBoss;
    }

    public synchronized AppMode setBoss(boolean boss) {
        isBoss = boss;
        return this;
    }

    public synchronized String getTel() {
        return tel;
    }

    public synchronized AppMode setTel(String tel) {
        this.tel = tel;
        return this;
    }

    private String tel;

    private AppMode() {

    }

    private static class Init {
        private static final AppMode INIT = new AppMode();
    }

    public static AppMode getInstance() {
        return Init.INIT;
    }
}
