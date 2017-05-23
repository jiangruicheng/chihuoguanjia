package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public class RequestAddPrint extends BaseRequest {

    /**
     * uid : 3
     * mid : 3
     * name : 家里打印机
     * key : 1234
     * code : 11234
     */

    private String uid;
    private String mid;
    private String name;
    private String key;
    private String code;

    public String getUid() {
        return uid;
    }

    public RequestAddPrint setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public RequestAddPrint setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestAddPrint setName(String name) {
        this.name = name;
        return this;
    }

    public String getKey() {
        return key;
    }

    public RequestAddPrint setKey(String key) {
        this.key = key;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RequestAddPrint setCode(String code) {
        this.code = code;
        return this;
    }
}
