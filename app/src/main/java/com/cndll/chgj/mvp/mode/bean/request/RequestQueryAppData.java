package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/6/13.
 */

public class RequestQueryAppData extends BaseRequest {
    public String getMid() {
        return mid;
    }

    public RequestQueryAppData setMid(String mid) {
        this.mid = mid;
        return this;
    }

   private String mid;
}
