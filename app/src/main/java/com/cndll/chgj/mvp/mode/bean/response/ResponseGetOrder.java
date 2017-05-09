package com.cndll.chgj.mvp.mode.bean.response;

import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;

import java.util.List;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public class ResponseGetOrder extends BaseResponse {


    /**
     * code : 1
     * data : {"id":"45","ordnum":"2017-05-05-8662","tabname":"d01","uid":"10","mid":"23","type":"2","pernum":"2","payee":"MCMilo","items":[{"code":119,"count":1,"cre_tm":1493710568,"dc_id":14,"giveCount":1,"id":23,"is_discount":1,"is_over":0,"is_print":1,"machine":"厨房打印机","mid":23,"name":"food","ord":4,"price":"0.10","uid":10,"unit":"盘"}],"tmoney":"0.10","zk":"0","zkmoney":"0.00","smoney":"0.00","ysmoney":"0.00","ssmoney":"0.10","cre_tm":"1493978662","e_tm":null,"paytype":null,"tcmoney":null,"tab_id":"33","ordernum":"0","ym":null,"note":null,"allremarks":[],"writedishs":null,"storename":"sj","type_txt":"未付款"}
     * extra : 获取成功
     */


    /**
     * id : 45
     * ordnum : 2017-05-05-8662
     * tabname : d01
     * uid : 10
     * mid : 23
     * type : 2
     * pernum : 2
     * payee : MCMilo
     * items : [{"code":119,"count":1,"cre_tm":1493710568,"dc_id":14,"giveCount":1,"id":23,"is_discount":1,"is_over":0,"is_print":1,"machine":"厨房打印机","mid":23,"name":"food","ord":4,"price":"0.10","uid":10,"unit":"盘"}]
     * tmoney : 0.10
     * zk : 0
     * zkmoney : 0.00
     * smoney : 0.00
     * ysmoney : 0.00
     * ssmoney : 0.10
     * cre_tm : 1493978662
     * e_tm : null
     * paytype : null
     * tcmoney : null
     * tab_id : 33
     * ordernum : 0
     * ym : null
     * note : null
     * allremarks : []
     * writedishs : null
     * storename : sj
     * type_txt : 未付款
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        private String id;
        private String ordnum;
        private String tabname;
        private String uid;
        private String mid;
        private String type;
        private String pernum;
        private String payee;
        private String tmoney;
        private String zk;
        private String zkmoney;
        private String smoney;
        private String ysmoney;
        private String ssmoney;
        private String cre_tm;
        private Object e_tm;
        private Object paytype;
        private Object tcmoney;
        private String tab_id;
        private String ordernum;
        private Object ym;
        private Object note;
        private Object writedishs;
        private String storename;
        private String type_txt;
        /**
         * code : 119
         * count : 1
         * cre_tm : 1493710568
         * dc_id : 14
         * giveCount : 1
         * id : 23
         * is_discount : 1
         * is_over : 0
         * is_print : 1
         * machine : 厨房打印机
         * mid : 23
         * name : food
         * ord : 4
         * price : 0.10
         * uid : 10
         * unit : 盘
         */

        private List<ResponseGetCaipinList.DataBean> items;
        private List<RequestOrder.AllremarksBean> allremarks;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrdnum() {
            return ordnum;
        }

        public void setOrdnum(String ordnum) {
            this.ordnum = ordnum;
        }

        public String getTabname() {
            return tabname;
        }

        public void setTabname(String tabname) {
            this.tabname = tabname;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public Object getE_tm() {
            return e_tm;
        }

        public void setE_tm(Object e_tm) {
            this.e_tm = e_tm;
        }

        public Object getPaytype() {
            return paytype;
        }

        public void setPaytype(Object paytype) {
            this.paytype = paytype;
        }

        public Object getTcmoney() {
            return tcmoney;
        }

        public void setTcmoney(Object tcmoney) {
            this.tcmoney = tcmoney;
        }

        public String getTab_id() {
            return tab_id;
        }

        public void setTab_id(String tab_id) {
            this.tab_id = tab_id;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public Object getYm() {
            return ym;
        }

        public void setYm(Object ym) {
            this.ym = ym;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public Object getWritedishs() {
            return writedishs;
        }

        public void setWritedishs(Object writedishs) {
            this.writedishs = writedishs;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getType_txt() {
            return type_txt;
        }

        public void setType_txt(String type_txt) {
            this.type_txt = type_txt;
        }

        public List<ResponseGetCaipinList.DataBean> getItems() {
            return items;
        }

        public void setItems(List<ResponseGetCaipinList.DataBean> items) {
            this.items = items;
        }

        public List<RequestOrder.AllremarksBean> getAllremarks() {
            return allremarks;
        }

        public void setAllremarks(List<RequestOrder.AllremarksBean> allremarks) {
            this.allremarks = allremarks;
        }

        public static class ItemsBean {
            private int code;
            private int count;
            private int cre_tm;
            private int dc_id;
            private int giveCount;
            private int id;
            private int is_discount;
            private int is_over;
            private int is_print;
            private String machine;
            private int mid;
            private String name;
            private int ord;
            private String price;
            private int uid;
            private String unit;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getCre_tm() {
                return cre_tm;
            }

            public void setCre_tm(int cre_tm) {
                this.cre_tm = cre_tm;
            }

            public int getDc_id() {
                return dc_id;
            }

            public void setDc_id(int dc_id) {
                this.dc_id = dc_id;
            }

            public int getGiveCount() {
                return giveCount;
            }

            public void setGiveCount(int giveCount) {
                this.giveCount = giveCount;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_discount() {
                return is_discount;
            }

            public void setIs_discount(int is_discount) {
                this.is_discount = is_discount;
            }

            public int getIs_over() {
                return is_over;
            }

            public void setIs_over(int is_over) {
                this.is_over = is_over;
            }

            public int getIs_print() {
                return is_print;
            }

            public void setIs_print(int is_print) {
                this.is_print = is_print;
            }

            public String getMachine() {
                return machine;
            }

            public void setMachine(String machine) {
                this.machine = machine;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrd() {
                return ord;
            }

            public void setOrd(int ord) {
                this.ord = ord;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}
