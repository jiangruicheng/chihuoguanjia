package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/3.
 */

public class RequestMendianOrd extends BaseRequest{

    /**
     * id : 3
     * ord : 2
     */

    private int id;
    private int ord;

    public int getId() {
        return id;
    }

    public RequestMendianOrd setId(int id) {
        this.id = id;
        return this;
    }

    public int getOrd() {
        return ord;
    }

    public RequestMendianOrd setOrd(int ord) {
        this.ord = ord;
        return this;
    }
}
