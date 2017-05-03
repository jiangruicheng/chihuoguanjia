package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/2.
 */

public class RequestGetCaipinList {
    /**
     * mid : 3
     * uid : 3
     * dc_id :
     * name :
     */

    private String mid;
    private String uid;
    private String dc_id;
    private String name;

    public String getMid() {
        return mid;
    }

    public RequestGetCaipinList setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestGetCaipinList setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getDc_id() {
        return dc_id;
    }

    public RequestGetCaipinList setDc_id(String dc_id) {
        this.dc_id = dc_id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestGetCaipinList setName(String name) {
        this.name = name;
        return this;
    }
}
