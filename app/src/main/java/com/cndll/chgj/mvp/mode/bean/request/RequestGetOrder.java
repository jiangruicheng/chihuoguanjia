package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public class RequestGetOrder extends BaseRequest{
    public int getId() {
        return id;
    }

    public RequestGetOrder setId(int id) {
        this.id = id;
        return this;
    }

    private int id;
}
