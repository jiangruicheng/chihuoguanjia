package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/6/6.
 */

public class RequestDeleteStaff extends BaseRequest {
    public String getStaff_uid() {
        return staff_uid;
    }

    public RequestDeleteStaff setStaff_uid(String staff_uid) {
        this.staff_uid = staff_uid;
        return this;
    }

    private String staff_uid;

}
