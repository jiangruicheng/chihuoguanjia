package com.cndll.chgj.mvp.mode.bean.response;

import com.cndll.chgj.adapter.DataList;

import java.util.List;

/**
 * Created by kongqing on 2017/5/2.
 */

public class ResponseGetCaipinList extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"2","name":"青椒炒肉","unit":"盘","is_discount":"1","price":"18.00","is_over":"0","machine":"厨房打印","is_print":"1","code":"120","mid":"3","uid":"3","ord":"2","cre_tm":"1490758958","dc_id":"3"},{"id":"7","name":"花甲","unit":"盘","is_discount":"1","price":"28.00","is_over":"0","machine":"厨房打印","is_print":"1","code":"011","mid":"3","uid":"3","ord":"3","cre_tm":"1491892621","dc_id":"3"},{"id":"24","name":"青椒炒肉","unit":"盘","is_discount":"1","price":"18.00","is_over":"0","machine":"厨房打印","is_print":"1","code":"120","mid":"3","uid":"3","ord":"4","cre_tm":"1493726840","dc_id":"3"}]
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
        @Override
        public String getID() {
            return id;
        }

        @Override
        public void setOrder(int i) {
            ord = String.valueOf(i);
        }

        /**
         * id : 2
         * name : 青椒炒肉
         * unit : 盘
         * is_discount : 1
         * price : 18.00
         * is_over : 0
         * machine : 厨房打印
         * is_print : 1
         * code : 120
         * mid : 3
         * uid : 3
         * ord : 2
         * cre_tm : 1490758958
         * dc_id : 3
         */

        private String id;
        private String name;
        private String unit;
        private String is_discount;
        private String price;
        private String is_over;
        private String machine;
        private String is_print;
        private String code;
        private String mid;
        private String uid;
        private String ord;
        private String cre_tm;
        private String dc_id;

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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getIs_discount() {
            return is_discount;
        }

        public void setIs_discount(String is_discount) {
            this.is_discount = is_discount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIs_over() {
            return is_over;
        }

        public void setIs_over(String is_over) {
            this.is_over = is_over;
        }

        public String getMachine() {
            return machine;
        }

        public void setMachine(String machine) {
            this.machine = machine;
        }

        public String getIs_print() {
            return is_print;
        }

        public void setIs_print(String is_print) {
            this.is_print = is_print;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getOrd() {
            return ord;
        }

        public void setOrd(String ord) {
            this.ord = ord;
        }

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public String getDc_id() {
            return dc_id;
        }

        public void setDc_id(String dc_id) {
            this.dc_id = dc_id;
        }
    }
}
