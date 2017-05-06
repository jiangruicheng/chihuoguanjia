package com.cndll.chgj.mvp.mode.bean.request;

import java.util.List;

/**
 * Created by kongqing on 2017/5/4.
 */

public class RequestOrder {
    /**
     * tab_id : 1
     * uid : 3
     * mid : 3
     * tabname : A0001
     * pernum : 2
     * payee : 王五
     * tmoney : 100
     * zk : 8
     * zkmoney : 20
     * smoney : 10
     * ysmoney : 80
     * ssmoney : 79
     * items : [{"dish_name":"宫保鸡丁1","dish_num":1,"dish_money":24,"dish_method":"加辣"},{"dish_name":"宫保鸡丁2","dish_num":1,"dish_money":24,"dish_method":"少言"},{"dish_name":"宫保鸡丁3","dish_num":1,"dish_money":24,"dish_method":"退1"}]
     */

    private String tab_id;
    private String uid;
    private String mid;
    private String tabname;
    private String pernum;
    private String payee;
    private String tmoney;
    private String zk;
    private String zkmoney;
    private String smoney;
    private String ysmoney;
    private String ssmoney;
    private List<ItemsBean> items;

    public String getTab_id() {
        return tab_id;
    }

    public RequestOrder setTab_id(String tab_id) {
        this.tab_id = tab_id;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestOrder setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public RequestOrder setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getTabname() {
        return tabname;
    }

    public RequestOrder setTabname(String tabname) {
        this.tabname = tabname;
        return this;
    }

    public String getPernum() {
        return pernum;
    }

    public RequestOrder setPernum(String pernum) {
        this.pernum = pernum;
        return this;
    }

    public String getPayee() {
        return payee;
    }

    public RequestOrder setPayee(String payee) {
        this.payee = payee;
        return this;
    }

    public String getTmoney() {
        return tmoney;
    }

    public RequestOrder setTmoney(String tmoney) {
        this.tmoney = tmoney;
        return this;
    }

    public String getZk() {
        return zk;
    }

    public RequestOrder setZk(String zk) {
        this.zk = zk;
        return this;
    }

    public String getZkmoney() {
        return zkmoney;
    }

    public RequestOrder setZkmoney(String zkmoney) {
        this.zkmoney = zkmoney;
        return this;
    }

    public String getSmoney() {
        return smoney;
    }

    public RequestOrder setSmoney(String smoney) {
        this.smoney = smoney;
        return this;
    }

    public String getYsmoney() {
        return ysmoney;
    }

    public RequestOrder setYsmoney(String ysmoney) {
        this.ysmoney = ysmoney;
        return this;
    }

    public String getSsmoney() {
        return ssmoney;
    }

    public RequestOrder setSsmoney(String ssmoney) {
        this.ssmoney = ssmoney;
        return this;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public RequestOrder setItems(List<ItemsBean> items) {
        this.items = items;
        return this;
    }

    public static class ItemsBean {
        public String getId() {
            return id;
        }

        public ItemsBean setId(String id) {
            this.id = id;
            return this;
        }

        /**
         * dish_name : 宫保鸡丁1
         * dish_num : 1
         * dish_money : 24
         * dish_method : 加辣
         */
        private String id;
        private String dish_name;
        private int dish_num;
        private float dish_money;

        public float getDish_mmoney() {
            return dish_mmoney;
        }

        public ItemsBean setDish_mmoney(float dish_mmoney) {
            this.dish_mmoney = dish_mmoney;
            return this;
        }

        private float dish_mmoney;

        private String dish_method;

        public String getDish_name() {
            return dish_name;
        }

        public ItemsBean setDish_name(String dish_name) {
            this.dish_name = dish_name;
            return this;
        }

        public int getDish_num() {
            return dish_num;
        }

        public ItemsBean setDish_num(int dish_num) {
            this.dish_num = dish_num;
            return this;
        }

        public float getDish_money() {
            return dish_money;
        }

        public ItemsBean setDish_money(float dish_money) {
            this.dish_money = dish_money;
            return this;
        }

        public String getDish_method() {
            return dish_method;
        }

        public ItemsBean setDish_method(String dish_method) {
            this.dish_method = dish_method;
            return this;
        }
    }
}
