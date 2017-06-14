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

    public String getUsername() {
        return username;
    }

    public AppMode setUsername(String username) {
        this.username = username;
        return this;
    }

    private String username;

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
    private boolean isDiscount;
    private boolean isOrder;
    private boolean isExcel;

    public boolean isAppOver() {
        return isAppOver;
    }

    public void setAppOver(boolean appOver) {
        isAppOver = appOver;
    }

    private boolean isAppOver;

    public synchronized boolean isDiscount() {
        return isDiscount;
    }

    public synchronized void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public synchronized boolean isOrder() {
        return isOrder;
    }

    public synchronized void setOrder(boolean order) {
        isOrder = order;
    }

    public synchronized boolean isExcel() {
        return isExcel;
    }

    public synchronized void setExcel(boolean excel) {
        isExcel = excel;
    }

    public synchronized boolean isReturn() {
        return isReturn;
    }

    public synchronized void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }

    public synchronized boolean isGive() {
        return isGive;
    }

    public synchronized void setGive(boolean give) {
        isGive = give;
    }

    private boolean isReturn;
    private boolean isGive;

    public String getMcode() {
        return mcode;
    }

    public AppMode setMcode(String mcode) {
        this.mcode = mcode;
        return this;
    }

    private String mcode;

    private AppMode() {

    }

    private static class Init {
        private static final AppMode INIT = new AppMode();
    }

    public static AppMode getInstance() {
        return Init.INIT;
    }
}
