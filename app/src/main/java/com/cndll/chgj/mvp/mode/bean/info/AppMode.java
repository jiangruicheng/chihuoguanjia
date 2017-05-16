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

    public synchronized String getPrint_code() {
        return print_code;
    }

    public synchronized void setPrint_code(String print_code) {
        this.print_code = print_code;
    }

    private String print_code;

    public synchronized boolean isLoading() {
        return isLoading;
    }

    public synchronized AppMode setLoading(boolean loading) {
        isLoading = loading;
        return this;
    }

    private boolean isLoading;

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

    public boolean isDeskMode() {
        return isDeskMode;
    }

    public void setDeskMode(boolean deskMode) {
        isDeskMode = deskMode;
    }

    private boolean isDeskMode = true;

    private AppMode() {

    }

    private static class Init {
        private static final AppMode INIT = new AppMode();
    }

    public static AppMode getInstance() {
        return Init.INIT;
    }
}
