package com.cndll.chgj.mvp.mode.bean.request;

import com.cndll.chgj.mvp.mode.bean.info.AppMode;

import java.util.List;

/**
 * Created by kongqing on 2017/5/19.
 */

public class RequestPrintBackDesh {


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * tabcode : 桌台号
     * title : 标题
     * date : 201705-24
     * sname : 送单人
     * items : [{"name":"菜品名称","num":1,"unit":"份","money":"50","m_name":"做法(+做法价格)"}]
     */
    private String mid = AppMode.getInstance().getMid();
    private String uid = AppMode.getInstance().getUid();
    private String tabcode;
    private String title;
    private String date;
    private String sname;
    private List<ItemsBean> items;

    public String getTabcode() {
        return tabcode;
    }

    public RequestPrintBackDesh setTabcode(String tabcode) {
        this.tabcode = tabcode;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RequestPrintBackDesh setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDate() {
        return date;
    }

    public RequestPrintBackDesh setDate(String date) {
        this.date = date;
        return this;
    }

    public String getSname() {
        return sname;
    }

    public RequestPrintBackDesh setSname(String sname) {
        this.sname = sname;
        return this;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public RequestPrintBackDesh setItems(List<ItemsBean> items) {
        this.items = items;
        return this;
    }

    public static class ItemsBean {
        /**
         * name : 菜品名称
         * num : 1
         * unit : 份
         * money : 50
         * m_name : 做法(+做法价格)
         */

        private String name;
        private String num;
        private String unit;
        private String money;
        private String m_name;

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

        public String getMoney() {
            return money;
        }

        public ItemsBean setMoney(String money) {
            this.money = money;
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
