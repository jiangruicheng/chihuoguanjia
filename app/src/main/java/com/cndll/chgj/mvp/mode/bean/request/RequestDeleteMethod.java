package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/3.
 */

public class RequestDeleteMethod extends BaseRequest{
    public String getId() {
        return id;
    }

    public RequestDeleteMethod setId(String id) {
        this.id = id;
        return this;
    }

    String id;
}
