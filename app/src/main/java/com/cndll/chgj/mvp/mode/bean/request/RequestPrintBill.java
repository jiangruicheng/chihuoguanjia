package com.cndll.chgj.mvp.mode.bean.request;

import com.cndll.chgj.mvp.mode.bean.info.AppMode;

import java.util.List;

/**
 * Created by kongqing on 2017/6/14.
 */

public class RequestPrintBill extends BaseRequest {

    /**
     * mid : 3
     * uid : 3
     * date : 2017-05-19 11:27
     * sname : 老板
     * tabcode : A002
     * title : 出品分单
     * items : [{"money":"24","name":"西红柿鸡蛋炒饭","num":"7","unit":"盘","m_name":"(加辣椒+25)"}]
     */

    private String mid = AppMode.getInstance().getMid();
    private String uid = AppMode.getInstance().getUid();
    private String date;
    private String sname;
    private String tabcode;
    private String title;
    private List<ItemsBean> items;


    public String getDate() {
        return date;
    }

    public RequestPrintBill setDate(String date) {
        this.date = date;
        return this;
    }

    public String getSname() {
        return sname;
    }

    public RequestPrintBill setSname(String sname) {
        this.sname = sname;
        return this;
    }

    public String getTabcode() {
        return tabcode;
    }

    public RequestPrintBill setTabcode(String tabcode) {
        this.tabcode = tabcode;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RequestPrintBill setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public RequestPrintBill setItems(List<ItemsBean> items) {
        this.items = items;
        return this;
    }

    public static class ItemsBean {
        /**
         * money : 24
         * name : 西红柿鸡蛋炒饭
         * num : 7
         * unit : 盘
         * m_name : (加辣椒+25)
         */

        private String money;
        private String name;
        private String num;
        private String unit;
        private String m_name;

        public String getMoney() {
            return money;
        }

        public ItemsBean setMoney(String money) {
            this.money = money;
            return this;
        }

        public String getName() {
            return name;
        }

        public ItemsBean setName(String name) {
            this.name = name;
            return this;
        }

        public String getNum() {
            return num;
        }

        public ItemsBean setNum(String num) {
            this.num = num;
            return this;
        }

        public String getUnit() {
            return unit;
        }

        public ItemsBean setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public String getM_name() {
            return m_name;
        }

        public ItemsBean setM_name(String m_name) {
            this.m_name = m_name;
            return this;
        }
    }
}
