package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/3.
 */

public class RequestUpdaCaipin {
    private int mid;
    private int uid;
    private String name;
    private String unit;
    private int is_discount;
    private int price;
    private int is_over;
    private String machine;
    private int is_print;
    private String code;
    private int dc_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public int getMid() {
        return mid;
    }

    public RequestUpdaCaipin setMid(int mid) {
        this.mid = mid;
        return this;
    }

    public int getUid() {
        return uid;
    }

    public RequestUpdaCaipin setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestUpdaCaipin setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public RequestUpdaCaipin setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public int getIs_discount() {
        return is_discount;
    }

    public RequestUpdaCaipin setIs_discount(int is_discount) {
        this.is_discount = is_discount;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public RequestUpdaCaipin setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getIs_over() {
        return is_over;
    }

    public RequestUpdaCaipin setIs_over(int is_over) {
        this.is_over = is_over;
        return this;
    }

    public String getMachine() {
        return machine;
    }

    public RequestUpdaCaipin setMachine(String machine) {
        this.machine = machine;
        return this;
    }

    public int getIs_print() {
        return is_print;
    }

    public RequestUpdaCaipin setIs_print(int is_print) {
        this.is_print = is_print;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RequestUpdaCaipin setCode(String code) {
        this.code = code;
        return this;
    }

    public int getDc_id() {
        return dc_id;
    }

    public RequestUpdaCaipin setDc_id(int dc_id) {
        this.dc_id = dc_id;
        return this;
    }
}
