package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/2.
 */

public class RequestPrintList {
    public String getMid() {
        return mid;
    }

    public RequestPrintList setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestPrintList setUid(String uid) {
        this.uid = uid;
        return this;
    }

    String mid;
    String uid;
}
