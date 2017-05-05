package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/4/26.
 */

public class RequestGetMethodList {

    /**
     * mid : 3
     * name : 小炒类
     * uid : 3
     */

    private String mid;
    private String name;
    private String uid;

    public String getMid() {
        return mid;
    }

    public RequestGetMethodList setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestGetMethodList setName(String name) {
        this.name = name;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestGetMethodList setUid(String uid) {
        this.uid = uid;
        return this;
    }
}

