package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/3.
 */

public class RequestUpdaDesk extends BaseRequest{
    public String getName() {
        return name;
    }

    public RequestUpdaDesk setName(String name) {
        this.name = name;
        return this;
    }

    String name;

    public String getId() {
        return id;
    }

    public RequestUpdaDesk setId(String id) {
        this.id = id;
        return this;
    }

    String id;
}
