package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/5/16.
 */

public class ResponseBank extends BaseResponse {

    /**
     * code : 1
     * data : ["中国建设银行","恒丰银行","中国人民银行","交通银行","中国银行"]
     * extra : 获取成功
     */


    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
