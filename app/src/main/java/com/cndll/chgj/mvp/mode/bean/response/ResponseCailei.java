package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/4/26.
 */

public class ResponseCailei  extends BaseResponse{

    /**
     * code : 1
     * data :
     * extra : 添加成功
     */


    private String data;




    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
