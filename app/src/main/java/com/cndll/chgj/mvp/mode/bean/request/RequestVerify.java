package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/4/26.
 */

public class RequestVerify extends BaseRequest{
    /**
     * tel : 15001372759
     */

    private String tel;

    public String getTel() {
        return tel;
    }

    public RequestVerify setTel(String tel) {
        this.tel = tel;return this;
    }
}
