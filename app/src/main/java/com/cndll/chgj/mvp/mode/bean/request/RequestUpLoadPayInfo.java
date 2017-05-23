package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/16.
 */

public class RequestUpLoadPayInfo extends BaseRequest{
    String code;
    String province_id;
    String city_id;
    String bankaddress;
    String name;
    String banknum;
    String tel;
    String mid;
    String uid;
    String cert_1;
    String cert_2;
    String cert_3;

    public String getCode() {
        return code;
    }

    public RequestUpLoadPayInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getProvince_id() {
        return province_id;
    }

    public RequestUpLoadPayInfo setProvince_id(String province_id) {
        this.province_id = province_id;
        return this;
    }

    public String getCity_id() {
        return city_id;
    }

    public RequestUpLoadPayInfo setCity_id(String city_id) {
        this.city_id = city_id;
        return this;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public RequestUpLoadPayInfo setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestUpLoadPayInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getBanknum() {
        return banknum;
    }

    public RequestUpLoadPayInfo setBanknum(String banknum) {
        this.banknum = banknum;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public RequestUpLoadPayInfo setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public RequestUpLoadPayInfo setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestUpLoadPayInfo setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getCert_1() {
        return cert_1;
    }

    public RequestUpLoadPayInfo setCert_1(String cert_1) {
        this.cert_1 = cert_1;
        return this;
    }

    public String getCert_2() {
        return cert_2;
    }

    public RequestUpLoadPayInfo setCert_2(String cert_2) {
        this.cert_2 = cert_2;
        return this;
    }

    public String getCert_3() {
        return cert_3;
    }

    public RequestUpLoadPayInfo setCert_3(String cert_3) {
        this.cert_3 = cert_3;
        return this;
    }
}
