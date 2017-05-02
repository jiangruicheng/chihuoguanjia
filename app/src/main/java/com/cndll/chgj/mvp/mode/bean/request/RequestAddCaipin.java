package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/2.
 */

public class RequestAddCaipin {

    /**
     * mid : 3
     * uid : 3
     * name : 青椒炒肉
     * unit : 盘
     * is_discount : 1
     * price : 18
     * is_over : 0
     * machine : 厨房打印
     * is_print : 1
     * code : 120
     * dc_id : 3
     */

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

    public int getMid() {
        return mid;
    }

    public RequestAddCaipin setMid(int mid) {
        this.mid = mid;
        return this;
    }

    public int getUid() {
        return uid;
    }

    public RequestAddCaipin setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestAddCaipin setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public RequestAddCaipin setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public int getIs_discount() {
        return is_discount;
    }

    public RequestAddCaipin setIs_discount(int is_discount) {
        this.is_discount = is_discount;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public RequestAddCaipin setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getIs_over() {
        return is_over;
    }

    public RequestAddCaipin setIs_over(int is_over) {
        this.is_over = is_over;
        return this;
    }

    public String getMachine() {
        return machine;
    }

    public RequestAddCaipin setMachine(String machine) {
        this.machine = machine;
        return this;
    }

    public int getIs_print() {
        return is_print;
    }

    public RequestAddCaipin setIs_print(int is_print) {
        this.is_print = is_print;
        return this;
    }

    public String getCode() {
        return code;
    }

    public RequestAddCaipin setCode(String code) {
        this.code = code;
        return this;
    }

    public int getDc_id() {
        return dc_id;
    }

    public RequestAddCaipin setDc_id(int dc_id) {
        this.dc_id = dc_id;
        return this;
    }
}
