package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by kongqing on 2017/5/2.
 */

public class RequestAddCaipin extends BaseRequest{

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

    private String mid;
    private String uid;
    private String name;
    private String unit;
    private int is_discount;
    private String price;
    private int is_over;
    private String machine;
    private int is_print;
    private String code;
    private String dc_id;

    public String getMachine_name() {
        return machine_name;
    }

    public RequestAddCaipin setMachine_name(String machine_name) {
        this.machine_name = machine_name;
        return this;
    }

    private String machine_name;
    public String getMid() {
        return mid;
    }

    public RequestAddCaipin setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestAddCaipin setUid(String uid) {
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

    public String getPrice() {
        return price;
    }

    public RequestAddCaipin setPrice(String price) {
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

    public String getDc_id() {
        return dc_id;
    }

    public RequestAddCaipin setDc_id(String dc_id) {
        this.dc_id = dc_id;
        return this;
    }
}
