package com.cndll.chgj.mvp.mode.bean.request;

import java.util.List;

/**
 * Created by kongqing on 2017/5/4.
 */

public class RquestOrder {
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

    public void setTab_id(String tab_id) {
        this.tab_id = tab_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getTabname() {
        return tabname;
    }

    public void setTabname(String tabname) {
        this.tabname = tabname;
    }

    public String getPernum() {
        return pernum;
    }

    public void setPernum(String pernum) {
        this.pernum = pernum;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getTmoney() {
        return tmoney;
    }

    public void setTmoney(String tmoney) {
        this.tmoney = tmoney;
    }

    public String getZk() {
        return zk;
    }

    public void setZk(String zk) {
        this.zk = zk;
    }

    public String getZkmoney() {
        return zkmoney;
    }

    public void setZkmoney(String zkmoney) {
        this.zkmoney = zkmoney;
    }

    public String getSmoney() {
        return smoney;
    }

    public void setSmoney(String smoney) {
        this.smoney = smoney;
    }

    public String getYsmoney() {
        return ysmoney;
    }

    public void setYsmoney(String ysmoney) {
        this.ysmoney = ysmoney;
    }

    public String getSsmoney() {
        return ssmoney;
    }

    public void setSsmoney(String ssmoney) {
        this.ssmoney = ssmoney;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * dish_name : 宫保鸡丁1
         * dish_num : 1
         * dish_money : 24
         * dish_method : 加辣
         */

        private String dish_name;
        private int dish_num;
        private int dish_money;
        private String dish_method;

        public String getDish_name() {
            return dish_name;
        }

        public void setDish_name(String dish_name) {
            this.dish_name = dish_name;
        }

        public int getDish_num() {
            return dish_num;
        }

        public void setDish_num(int dish_num) {
            this.dish_num = dish_num;
        }

        public int getDish_money() {
            return dish_money;
        }

        public void setDish_money(int dish_money) {
            this.dish_money = dish_money;
        }

        public String getDish_method() {
            return dish_method;
        }

        public void setDish_method(String dish_method) {
            this.dish_method = dish_method;
        }
    }
}
