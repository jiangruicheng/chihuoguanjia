package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/8.
 */

public class RequestUpdateStaff extends BaseRequest{
    public String getId() {
        return id;
    }

    public RequestUpdateStaff setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * tel : 15220163618
     * password : 123456
     * username : 小杨
     * mid : 3
     * qx : 1,2,3,4,5
     */
    private String id;
    private String tel;
    private String password;

    private String username;
    private String mid;
    private String qx;

    public String getTel() {
        return tel;
    }

    public RequestUpdateStaff setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RequestUpdateStaff setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RequestUpdateStaff setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public RequestUpdateStaff setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getQx() {
        return qx;
    }

    public RequestUpdateStaff setQx(String qx) {
        this.qx = qx;
        return this;
    }
}
