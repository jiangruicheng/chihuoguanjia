package com.cndll.chgj.mvp.mode;

/**
 * Created by kongqing on 2017/4/5.
 */

public class AppRequestResoult {
    public synchronized String getMid() {
        return mid;
    }

    public synchronized AppRequestResoult setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public synchronized String getToken() {
        return token;
    }

    public synchronized AppRequestResoult setToken(String token) {
        this.token = token;
        return this;
    }

    public synchronized String getUid() {
        return uid;
    }

    public synchronized AppRequestResoult setUid(String uid) {
        this.uid = uid;
        return this;
    }

    private String mid;
    private String token;
    private String uid;
    private boolean isBoss;


    private AppRequestResoult() {

    }

    private static class Init {
        private static final AppRequestResoult INIT = new AppRequestResoult();
    }

    public static AppRequestResoult getInstance() {
        return Init.INIT;
    }
}
