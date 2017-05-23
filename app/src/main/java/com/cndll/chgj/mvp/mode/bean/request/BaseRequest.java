package com.cndll.chgj.mvp.mode.bean.request;

import com.cndll.chgj.mvp.mode.bean.info.AppMode;

/**
 * Created by kongqing on 2017/5/22.
 */

public class BaseRequest {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    String token = AppMode.getInstance().getToken();
}
