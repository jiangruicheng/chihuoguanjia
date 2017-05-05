package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/4.
 */

public class RequestAddMethod {
    private String uid;

    public String getMid() {
        return mid;
    }

    public RequestAddMethod setMid(String mid) {
        this.mid = mid;
        return this;
    }

    private String mid;

    public String getUid() {
        return uid;
    }

    public RequestAddMethod setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestAddMethod setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public RequestAddMethod setPrice(String price) {
        this.price = price;
        return this;
    }

    private String name;
    private String price;
}
