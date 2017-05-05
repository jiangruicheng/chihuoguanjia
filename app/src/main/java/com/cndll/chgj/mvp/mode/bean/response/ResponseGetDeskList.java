package com.cndll.chgj.mvp.mode.bean.response;

import com.cndll.chgj.adapter.DataList;

import java.util.List;

/**
 * Created by kongqing on 2017/5/5.
 */

public class ResponseGetDeskList extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"2","name":"A0001","mid":"3","uid":"3","cre_tm":"1490771296","ord":"2","num":"0","money":"20.00","isoc":"1","oid":0},{"id":"19","name":"add","mid":"3","uid":"3","cre_tm":"1492226010","ord":"19","num":"0","money":null,"isoc":"0","oid":0},{"id":"20","name":"A001","mid":"3","uid":"3","cre_tm":"1492242421","ord":"20","num":"0","money":null,"isoc":"0","oid":0},{"id":"21","name":"A001","mid":"3","uid":"3","cre_tm":"1492242422","ord":"21","num":"0","money":null,"isoc":"0","oid":0},{"id":"22","name":"A001","mid":"3","uid":"3","cre_tm":"1492242423","ord":"22","num":"0","money":null,"isoc":"0","oid":0},{"id":"23","name":"A001","mid":"3","uid":"3","cre_tm":"1492242423","ord":"23","num":"0","money":null,"isoc":"0","oid":0},{"id":"24","name":"A001","mid":"3","uid":"3","cre_tm":"1492242424","ord":"24","num":"0","money":null,"isoc":"0","oid":0},{"id":"25","name":"A001","mid":"3","uid":"3","cre_tm":"1492242425","ord":"25","num":"0","money":null,"isoc":"0","oid":0},{"id":"26","name":"A001","mid":"3","uid":"3","cre_tm":"1492242425","ord":"26","num":"0","money":null,"isoc":"0","oid":0},{"id":"27","name":"A002","mid":"3","uid":"3","cre_tm":"1492242444","ord":"27","num":"0","money":null,"isoc":"0","oid":0},{"id":"34","name":"A002","mid":"3","uid":"3","cre_tm":"1493704842","ord":"28","num":"0","money":null,"isoc":"0","oid":0}]
     * extra : 获取成功
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends DataList {
        /**
         * id : 2
         * name : A0001
         * mid : 3
         * uid : 3
         * cre_tm : 1490771296
         * ord : 2
         * num : 0
         * money : 20.00
         * isoc : 1
         * oid : 0
         */

        private String id;
        private String name;
        private String mid;
        private String uid;
        private String cre_tm;
        private String ord;
        private String num;
        private String money;
        private String isoc;
        private int oid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getIDList() {
            return id;
        }

        @Override
        public int getOrderList() {
            return Integer.valueOf(this.ord);
        }

        @Override
        public void setOrderList(int i) {
            this.ord = String.valueOf(i);
        }

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

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public String getOrd() {
            return ord;
        }

        public void setOrd(String ord) {
            this.ord = ord;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getIsoc() {
            return isoc;
        }

        public void setIsoc(String isoc) {
            this.isoc = isoc;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }
    }
}
