package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/4/27.
 */

public class BaseResponse {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int code;
    
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String extra;

}
