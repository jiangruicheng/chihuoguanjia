package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/4/26.
 */

public class ResponseVerify extends BaseResponse {

    /**
     * code : 1
     * data : 588562
     * extra : 获取成功
     */

    private int data;


    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
