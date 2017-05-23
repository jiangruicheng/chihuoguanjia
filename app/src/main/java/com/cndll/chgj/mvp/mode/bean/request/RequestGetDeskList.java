package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/5.
 */

public class RequestGetDeskList extends BaseRequest{

    /**
     * uid : 3
     * mid : 3
     * name :
     * isoc :
     */

    private String uid;
    private String mid;
    private String name;
    private String isoc;

    public String getUid() {
        return uid;
    }

    public RequestGetDeskList setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public RequestGetDeskList setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestGetDeskList setName(String name) {
        this.name = name;
        return this;
    }

    public String getIsoc() {
        return isoc;
    }

    public RequestGetDeskList setIsoc(String isoc) {
        this.isoc = isoc;
        return this;
    }
}
