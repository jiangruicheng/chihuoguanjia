package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/5/3.
 */

public class ResponseOrd extends BaseResponse {

    /**
     * data : {}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }
}
