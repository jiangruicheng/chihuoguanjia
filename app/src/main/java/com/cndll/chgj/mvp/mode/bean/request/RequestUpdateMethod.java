package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/4.
 */

public class RequestUpdateMethod {
    public String getId() {
        return id;
    }

    public RequestUpdateMethod setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestUpdateMethod setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public RequestUpdateMethod setPrice(String price) {
        this.price = price;
        return this;
    }

    private String id;
    private String name;
    private String price;
}
