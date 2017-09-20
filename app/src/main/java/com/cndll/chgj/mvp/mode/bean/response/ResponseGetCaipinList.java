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

    public static class DataBean extends DataList implements Cloneable {

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
            ord = i + "";
        }

        public String getMachine_name() {
            return machine_name;
        }

        public void setMachine_name(String machine_name) {
            this.machine_name = machine_name;
        }

        public String getSmoney() {
            return smoney;
        }

        public void setSmoney(String smoney) {
            this.smoney = smoney;
        }

        public String getZkmoney() {
            return zkmoney;
        }

        public void setZkmoney(String zkmoney) {
            this.zkmoney = zkmoney;
        }

        public float getAddCount() {
            return addCount;
        }

        public DataBean setAddCount(float addCount) {
            this.addCount = addCount;
            return this;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public String getSalemoney() {
            return salemoney;
        }

        public void setSalemoney(String salemoney) {
            this.salemoney = salemoney;
        }

        /**
         * id : 81
         * unit : 份
         * mid : 51
         * machine : 厨房打印机
         * uid : 24
         * cre_tm : 1494001306
         * count : 1
         * giveCount : 0
         * price : 20.00
         * is_discount : 1
         * is_print : 1
         * code : 101
         * ord : 0
         * is_over : 0
         * remark : {"id":"81","remarks":[{"ord":"2","uid":"24","cre_tm":"1494001501","id":"51","price":"2.00","mid":"51","name":"加冰"}],"count":"1"}
         * name : 小炒肉
         * dc_id : 39
         */
        private String salemoney;
        private float addCount;
        private String smoney;
        private String zkmoney;
        private String machine_name;
        private String id;
        private String unit;
        private String mid;
        private String machine;
        private String uid;
        private String cre_tm;
        private String count;
        private int giveCount;
        private String price;
        private String is_discount;
        private String is_print;
        private String code;
        private String ord;
        private String is_over;
        private RemarkBean remark;
        private String name;
        private String dc_id;

        public float getFoodBackPrice() {
            return foodBackPrice;
        }

        public DataBean setFoodBackPrice(float foodBackPrice) {
            this.foodBackPrice = foodBackPrice;
            return this;
        }

        private float foodBackPrice;

        public String getBackCount() {
            return backCount;
        }

        public void setBackCount(String backCount) {
            this.backCount = backCount;
        }

        private String backCount;

        public String getId() {
            return id;
        }

        public DataBean setId(String id) {
            this.id = id;
            return this;
        }

        public String getUnit() {
            return unit;
        }

        public DataBean setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public String getMid() {
            return mid;
        }

        public DataBean setMid(String mid) {
            this.mid = mid;
            return this;
        }

        public String getMachine() {
            return machine;
        }

        public DataBean setMachine(String machine) {
            this.machine = machine;
            return this;
        }

        public String getUid() {
            return uid;
        }

        public DataBean setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public String getCre_tm() {
            return cre_tm;
        }

        public DataBean setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
            return this;
        }

        public String getCount() {
            return count;
        }

        public DataBean setCount(String count) {
            this.count = count;
            return this;
        }

        public int getGiveCount() {
            return giveCount;
        }

        public DataBean setGiveCount(int giveCount) {
            this.giveCount = giveCount;
            return this;
        }

        public String getPrice() {
            return price;
        }

        public DataBean setPrice(String price) {
            this.price = price;
            return this;
        }

        public String getIs_discount() {
            return is_discount;
        }

        public DataBean setIs_discount(String is_discount) {
            this.is_discount = is_discount;
            return this;
        }

        public String getIs_print() {
            return is_print;
        }

        public DataBean setIs_print(String is_print) {
            this.is_print = is_print;
            return this;
        }

        public String getCode() {
            return code;
        }

        public DataBean setCode(String code) {
            this.code = code;
            return this;
        }

        public String getOrd() {
            return ord;
        }

        public DataBean setOrd(String ord) {
            this.ord = ord;
            return this;
        }

        public String getIs_over() {
            return is_over;
        }

        public DataBean setIs_over(String is_over) {
            this.is_over = is_over;
            return this;
        }

        public RemarkBean getRemark() {
            return remark;
        }

        public void setRemark(RemarkBean remark) {
            this.remark = remark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDc_id() {
            return dc_id;
        }

        public void setDc_id(String dc_id) {
            this.dc_id = dc_id;
        }

        public static class RemarkBean {
            /**
             * id : 81
             * remarks : [{"ord":"2","uid":"24","cre_tm":"1494001501","id":"51","price":"2.00","mid":"51","name":"加冰"}]
             * count : 1
             */

            private String id;
            private String count;
            private List<ResponseMethod.DataBean> remarks;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public List<ResponseMethod.DataBean> getRemarks() {
                return remarks;
            }

            public void setRemarks(List<ResponseMethod.DataBean> remarks) {
                this.remarks = remarks;
            }

           /* public static class RemarksBean {
                *//**
             * ord : 2
             * uid : 24
             * cre_tm : 1494001501
             * id : 51
             * price : 2.00
             * mid : 51
             * name : 加冰
             *//*

                private String ord;
                private String uid;
                private String cre_tm;
                private String id;
                private String price;
                private String mid;
                private String name;

                public String getOrd() {
                    return ord;
                }

                public void setOrd(String ord) {
                    this.ord = ord;
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

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }*/
        }
    }
}
