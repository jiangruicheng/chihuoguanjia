package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public class RequestUpdatePrint extends BaseRequest{
    private String id;
    private String uid;
    private String mid;
    private String name;
    private String key;
    private String code;

    public String getUid() {
        return uid;
    }

    public RequestUpdatePrint setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public RequestUpdatePrint setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestUpdatePrint setName(String name) {
        this.name = name;
        return this;
    }

    public String getKey() {
        return key;
    }

    public RequestUpdatePrint setKey(String key) {
        this.key = key;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RequestUpdatePrint setCode(String code) {
        this.code = code;
        return this;
    }

    public String getId() {
        return id;
    }

    public RequestUpdatePrint setId(String id) {
        this.id = id;
        return this;
    }
}
