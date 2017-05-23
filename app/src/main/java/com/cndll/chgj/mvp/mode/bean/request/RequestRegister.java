package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/4/26.
 */

public class RequestRegister extends BaseRequest{

    /**
     * tel : 15001372759
     * name : 龙岗香菜馆
     * type : 香菜馆
     * province : 2
     * city : 500
     */

    private String tel;
    private String name;
    private String type;
    private int province;
    private int city;

    public String getTel() {
        return tel;
    }

    public RequestRegister setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestRegister setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public RequestRegister setType(String type) {
        this.type = type;
        return this;
    }

    public int getProvince() {
        return province;
    }

    public RequestRegister setProvince(int province) {
        this.province = province;
        return this;
    }

    public int getCity() {
        return city;
    }

    public RequestRegister setCity(int city) {
        this.city = city;
        return this;
    }
}
