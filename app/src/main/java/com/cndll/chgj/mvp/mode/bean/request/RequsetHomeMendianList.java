package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/4/14.
 */

public class RequsetHomeMendianList extends BaseRequest {
    public String getUid() {
        return uid;
    }

    public RequsetHomeMendianList setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String uid;
}
