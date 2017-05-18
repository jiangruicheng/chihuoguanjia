package com.cndll.chgj.mvp.mode.bean.request;

import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;

import java.util.List;

/**
 * Created by kongqing on 2017/5/4.
 */

public class RequestOrder {

    /**
     * code : 1
     * data : {"id":"57","ordnum":"2017-05-06-0025","tabname":"1号台","uid":"24","mid":"51","type":"0","pernum":"2","payee":"老板","items":[{"id":"84","unit":"份","mid":"51","machine":"厨房打印机","uid":"24","cre_tm":"1494001557","count":0,"giveCount":"0","price":"50.00","is_discount":"1","is_print":"1","code":"105","ord":"2","is_over":"0","name":"烤鱼","dc_id":"39"},{"id":"82","unit":"份","mid":"51","machine":"厨房打印机","uid":"24","cre_tm":"1494001359","count":0,"giveCount":"0","price":"18.00","is_discount":"1","is_print":"1","code":"102","ord":"1","is_over":"0","name":"清炒上海青","dc_id":"39"},{"id":"81","unit":"份","mid":"51","machine":"厨房打印机","uid":"24","cre_tm":"1494001306","count":"1","giveCount":0,"price":"20.00","is_discount":"1","is_print":"1","code":"101","ord":"0","is_over":"0","remark":{"id":"81","remarks":[{"ord":"2","uid":"24","cre_tm":"1494001501","id":"51","price":"2.00","mid":"51","name":"加冰"}],"count":"1"},"name":"小炒肉","dc_id":"39"}],"tmoney":"90.00","zk":"0","zkmoney":"0.00","smoney":"0.00","ysmoney":"22.00","ssmoney":"68.00","cre_tm":"1494030025","e_tm":null,"paytype":null,"tcmoney":null,"tab_id":"59","ordernum":"0","ym":null,"note":null,"allremarks":[{"id":"81","remarks":[{"ord":"2","uid":"24","cre_tm":"1494001501","id":"51","price":"2.00","mid":"51","name":"加冰"}],"count":"1"}],"writedishs":null,"storename":"食讯通","type_txt":"撤台"}
     * extra : 获取成功
     */


    /**
     * id : 57
     * ordnum : 2017-05-06-0025
     * tabname : 1号台
     * uid : 24
     * mid : 51
     * type : 0
     * pernum : 2
     * payee : 老板
     * items : [{"id":"84","unit":"份","mid":"51","machine":"厨房打印机","uid":"24","cre_tm":"1494001557","count":0,"giveCount":"0","price":"50.00","is_discount":"1","is_print":"1","code":"105","ord":"2","is_over":"0","name":"烤鱼","dc_id":"39"},{"id":"82","unit":"份","mid":"51","machine":"厨房打印机","uid":"24","cre_tm":"1494001359","count":0,"giveCount":"0","price":"18.00","is_discount":"1","is_print":"1","code":"102","ord":"1","is_over":"0","name":"清炒上海青","dc_id":"39"},{"id":"81","unit":"份","mid":"51","machine":"厨房打印机","uid":"24","cre_tm":"1494001306","count":"1","giveCount":0,"price":"20.00","is_discount":"1","is_print":"1","code":"101","ord":"0","is_over":"0","remark":{"id":"81","remarks":[{"ord":"2","uid":"24","cre_tm":"1494001501","id":"51","price":"2.00","mid":"51","name":"加冰"}],"count":"1"},"name":"小炒肉","dc_id":"39"}]
     * tmoney : 90.00
     * zk : 0
     * zkmoney : 0.00
     * smoney : 0.00
     * ysmoney : 22.00
     * ssmoney : 68.00
     * cre_tm : 1494030025
     * e_tm : null
     * paytype : null
     * tcmoney : null
     * tab_id : 59
     * ordernum : 0
     * ym : null
     * note : null
     * allremarks : [{"id":"81","remarks":[{"ord":"2","uid":"24","cre_tm":"1494001501","id":"51","price":"2.00","mid":"51","name":"加冰"}],"count":"1"}]
     * writedishs : null
     * storename : 食讯通
     * type_txt : 撤台
     */

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
    private List<WriteDishBean> writedishs;
    private String storename;
    private String type_txt;
    private List<ResponseGetCaipinList.DataBean> items;
    private List<ResponseGetCaipinList.DataBean.RemarkBean> allremarks;

    public String getId() {
        return id;
    }

    public RequestOrder setId(String id) {
        this.id = id;
        return this;
    }

    public String getOrdnum() {
        return ordnum;
    }

    public RequestOrder setOrdnum(String ordnum) {
        this.ordnum = ordnum;
        return this;
    }

    public String getTabname() {
        return tabname;
    }

    public RequestOrder setTabname(String tabname) {
        this.tabname = tabname;
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

    public String getType() {
        return type;
    }

    public RequestOrder setType(String type) {
        this.type = type;
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

    public String getCre_tm() {
        return cre_tm;
    }

    public RequestOrder setCre_tm(String cre_tm) {
        this.cre_tm = cre_tm;
        return this;
    }

    public Object getE_tm() {
        return e_tm;
    }

    public RequestOrder setE_tm(Object e_tm) {
        this.e_tm = e_tm;
        return this;
    }

    public Object getPaytype() {
        return paytype;
    }

    public RequestOrder setPaytype(Object paytype) {
        this.paytype = paytype;
        return this;
    }

    public Object getTcmoney() {
        return tcmoney;
    }

    public RequestOrder setTcmoney(Object tcmoney) {
        this.tcmoney = tcmoney;
        return this;
    }

    public String getTab_id() {
        return tab_id;
    }

    public RequestOrder setTab_id(String tab_id) {
        this.tab_id = tab_id;
        return this;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public RequestOrder setOrdernum(String ordernum) {
        this.ordernum = ordernum;
        return this;
    }

    public Object getYm() {
        return ym;
    }

    public RequestOrder setYm(Object ym) {
        this.ym = ym;
        return this;
    }

    public Object getNote() {
        return note;
    }

    public RequestOrder setNote(Object note) {
        this.note = note;
        return this;
    }

    public List<WriteDishBean> getWritedishs() {
        return writedishs;
    }

    public RequestOrder setWritedishs(List<WriteDishBean> writedishs) {
        this.writedishs = writedishs;
        return this;
    }

    public String getStorename() {
        return storename;
    }

    public RequestOrder setStorename(String storename) {
        this.storename = storename;
        return this;
    }

    public String getType_txt() {
        return type_txt;
    }

    public RequestOrder setType_txt(String type_txt) {
        this.type_txt = type_txt;
        return this;
    }

    public List<ResponseGetCaipinList.DataBean> getItems() {
        return items;
    }

    public RequestOrder setItems(List<ResponseGetCaipinList.DataBean> items) {
        this.items = items;
        return this;
    }

    public List<ResponseGetCaipinList.DataBean.RemarkBean> getAllremarks() {
        return allremarks;
    }

    public RequestOrder setAllremarks(List<ResponseGetCaipinList.DataBean.RemarkBean> allremarks) {
        this.allremarks = allremarks;
        return this;
    }

    public static class ItemsBean {
        /**
         * id : 84
         * unit : 份
         * mid : 51
         * machine : 厨房打印机
         * uid : 24
         * cre_tm : 1494001557
         * count : 0
         * giveCount : 0
         * price : 50.00
         * is_discount : 1
         * is_print : 1
         * code : 105
         * ord : 2
         * is_over : 0
         * name : 烤鱼
         * dc_id : 39
         * remark : {"id":"81","remarks":[{"ord":"2","uid":"24","cre_tm":"1494001501","id":"51","price":"2.00","mid":"51","name":"加冰"}],"count":"1"}
         */

        private String id;
        private String unit;
        private String mid;
        private String machine;
        private String uid;
        private String cre_tm;
        private int count;
        private String giveCount;
        private String price;
        private String is_discount;
        private String is_print;
        private String code;
        private String ord;
        private String is_over;
        private String name;
        private String dc_id;
        private ResponseGetCaipinList.DataBean.RemarkBean remark;

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

        public void setId(String id) {
            this.id = id;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getMachine() {
            return machine;
        }

        public void setMachine(String machine) {
            this.machine = machine;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getGiveCount() {
            return giveCount;
        }

        public void setGiveCount(String giveCount) {
            this.giveCount = giveCount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIs_discount() {
            return is_discount;
        }

        public void setIs_discount(String is_discount) {
            this.is_discount = is_discount;
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

        public String getOrd() {
            return ord;
        }

        public void setOrd(String ord) {
            this.ord = ord;
        }

        public String getIs_over() {
            return is_over;
        }

        public void setIs_over(String is_over) {
            this.is_over = is_over;
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

        public ResponseGetCaipinList.DataBean.RemarkBean getRemark() {
            return remark;
        }

        public void setRemark(ResponseGetCaipinList.DataBean.RemarkBean remark) {
            this.remark = remark;
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

            public static class RemarksBean {
                /**
                 * ord : 2
                 * uid : 24
                 * cre_tm : 1494001501
                 * id : 51
                 * price : 2.00
                 * mid : 51
                 * name : 加冰
                 */

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
            }
        }
    }

    public static class WriteDishBean {
        public String getCount() {
            return count;
        }

        public WriteDishBean setCount(String count) {
            this.count = count;
            return this;
        }

        public String getGiveCount() {
            return giveCount;
        }

        public WriteDishBean setGiveCount(String giveCount) {
            this.giveCount = giveCount;
            return this;
        }

        public String getIsWrite() {
            return isWrite;
        }

        public WriteDishBean setIsWrite(String isWrite) {
            this.isWrite = isWrite;
            return this;
        }

        public ResponseGetCaipinList.DataBean.RemarkBean getRemarks() {
            return remarks;
        }

        public WriteDishBean setRemarks(ResponseGetCaipinList.DataBean.RemarkBean remarks) {
            this.remarks = remarks;
            return this;
        }

        public String getPrice() {
            return price;
        }

        public WriteDishBean setPrice(String price) {
            this.price = price;
            return this;
        }

        public String getName() {
            return name;
        }

        public WriteDishBean setName(String name) {
            this.name = name;
            return this;
        }

        private String count;
        private String giveCount;
        private String isWrite;
        private ResponseGetCaipinList.DataBean.RemarkBean remarks;
        private String price;
        private String name;

        public String getBackCount() {
            return backCount;
        }

        public void setBackCount(String backCount) {
            this.backCount = backCount;
        }

        private String backCount;

    }

    public static class AllremarksBean {
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

        public AllremarksBean setId(String id) {
            this.id = id;
            return this;
        }

        public String getCount() {
            return count;
        }

        public AllremarksBean setCount(String count) {
            this.count = count;
            return this;
        }

        public List<ResponseMethod.DataBean> getRemarks() {
            return remarks;
        }

        public AllremarksBean setRemarks(List<ResponseMethod.DataBean> remarks) {
            this.remarks = remarks;
            return this;
        }

      /*  public static class RemarksBeanX {
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
