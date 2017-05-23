package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/4/26.
 */

public class RequestAddDesk extends BaseRequest {

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

    public RequestAddDesk setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestAddDesk setName(String name) {
        this.name = name;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestAddDesk setUid(String uid) {
        this.uid = uid;
        return this;
    }
}

