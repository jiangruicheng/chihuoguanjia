package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/15.
 */

public class RequestGetBillList {

    /**
     * uid : 3
     * mid : 3
     * stm : 2017-05-15
     * etm : 2017-05-15
     * name : 9989
     */

    private String uid;
    private String mid;
    private String stm;
    private String etm;
    private String name;

    public String getUid() {
        return uid;
    }

    public RequestGetBillList setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public RequestGetBillList setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getStm() {
        return stm;
    }

    public RequestGetBillList setStm(String stm) {
        this.stm = stm;
        return this;
    }

    public String getEtm() {
        return etm;
    }

    public RequestGetBillList setEtm(String etm) {
        this.etm = etm;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestGetBillList setName(String name) {
        this.name = name;
        return this;
    }
}
