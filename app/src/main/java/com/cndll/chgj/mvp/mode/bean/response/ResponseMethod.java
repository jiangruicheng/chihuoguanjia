package com.cndll.chgj.mvp.mode.bean.response;

import com.cndll.chgj.adapter.DataList;

import java.util.List;

/**
 * Created by kongqing on 2017/5/4.
 */

public class ResponseMethod extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"3","name":"加辣","price":null,"ord":"0","mid":"3","uid":"3","cre_tm":"1490774572"},{"id":"2","name":"加辣","price":"3.00","ord":"3","mid":"3","uid":"3","cre_tm":"1490774026"}]
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
         * id : 3
         * name : 加辣
         * price : null
         * ord : 0
         * mid : 3
         * uid : 3
         * cre_tm : 1490774572
         */

        private String id;
        private String name;
        private String price;
        private String ord;
        private String mid;
        private String uid;
        private String cre_tm;

        @Override
        public String getIDList() {
            return id;
        }

        @Override
        public int getOrderList() {
            return Integer.valueOf(ord);
        }

        @Override
        public void setOrderList(int i) {
            this.ord = String.valueOf(i);
        }

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrd() {
            return ord;
        }

        public void setOrd(String ord) {
            this.ord = ord;
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
    }
}
