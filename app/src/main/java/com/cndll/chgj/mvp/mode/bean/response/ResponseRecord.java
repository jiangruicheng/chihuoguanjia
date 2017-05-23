package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/5/23.
 */

public class ResponseRecord extends BaseResponse {

    /**
     * code : 1
     * data : {"storeinfo":{"name":"乡村18碗","code":"732820","overtm_tx":"2088-02-25"},"paylist":[{"id":"1","name":"1个月","money":"50.00","ord":"1","cre_tm":"1490803868","month":"1"},{"id":"2","name":"3个月","money":"130.00","ord":"2","cre_tm":"1491531494","month":"3"},{"id":"3","name":"6个月","money":"200.00","ord":"3","cre_tm":"1491531531","month":"6"},{"id":"4","name":"1年","money":"350.00","ord":"4","cre_tm":"1491531554","month":"12"},{"id":"5","name":"70年产权","money":"1080.00","ord":"5","cre_tm":"1491531585","month":"600"}]}
     * extra : 获取成功
     */


    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * storeinfo : {"name":"乡村18碗","code":"732820","overtm_tx":"2088-02-25"}
         * paylist : [{"id":"1","name":"1个月","money":"50.00","ord":"1","cre_tm":"1490803868","month":"1"},{"id":"2","name":"3个月","money":"130.00","ord":"2","cre_tm":"1491531494","month":"3"},{"id":"3","name":"6个月","money":"200.00","ord":"3","cre_tm":"1491531531","month":"6"},{"id":"4","name":"1年","money":"350.00","ord":"4","cre_tm":"1491531554","month":"12"},{"id":"5","name":"70年产权","money":"1080.00","ord":"5","cre_tm":"1491531585","month":"600"}]
         */

        private StoreinfoBean storeinfo;
        private List<PaylistBean> paylist;

        public StoreinfoBean getStoreinfo() {
            return storeinfo;
        }

        public void setStoreinfo(StoreinfoBean storeinfo) {
            this.storeinfo = storeinfo;
        }

        public List<PaylistBean> getPaylist() {
            return paylist;
        }

        public void setPaylist(List<PaylistBean> paylist) {
            this.paylist = paylist;
        }

        public static class StoreinfoBean {
            /**
             * name : 乡村18碗
             * code : 732820
             * overtm_tx : 2088-02-25
             */

            private String name;
            private String code;
            private String overtm_tx;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getOvertm_tx() {
                return overtm_tx;
            }

            public void setOvertm_tx(String overtm_tx) {
                this.overtm_tx = overtm_tx;
            }
        }

        public static class PaylistBean {
            /**
             * id : 1
             * name : 1个月
             * money : 50.00
             * ord : 1
             * cre_tm : 1490803868
             * month : 1
             */

            private String id;
            private String name;
            private String money;
            private String ord;
            private String cre_tm;
            private String month;

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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }
        }
    }
}
