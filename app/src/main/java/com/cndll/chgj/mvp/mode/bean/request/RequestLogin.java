package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/4/26.
 */

public class RequestLogin {

    /**
     * code : 129440
     * tel : 15001372759
     * password : 123456
     */

    private String code;
    private String tel;
    private String password;

    public String getCode() {
        return code;
    }

    public RequestLogin setCode(String code) {
        this.code = code;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public RequestLogin setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getPassword() {
        return password;

    }

    public RequestLogin setPassword(String password) {
        this.password = password;
        return this;
    }
}
