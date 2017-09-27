package com.cndll.chgj.mvp.mode.bean.response;


/**
 * Created by kongqing on 2017/5/19.
 */

public class ResponseGetSeting extends BaseResponse {


    /**
     * data : {"id":"2","uid":"3","mid":"3","tcis_print":"1","cd_method":"1","dis_zk":"1","cre_tm":"1492354820","weixin":"0","alipay":"0","isVerifing":1}
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
         * id : 2
         * uid : 3
         * mid : 3
         * tcis_print : 1
         * cd_method : 1
         * dis_zk : 1
         * cre_tm : 1492354820
         * weixin : 0
         * alipay : 0
         * isVerifing : 1
         */

        private String id;
        private String uid;
        private String mid;
        private String tcis_print;
        private String cd_method;
        private String dis_zk;
        private String cre_tm;
        private String weixin;
        private String alipay;
        private int isVerifing;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getTcis_print() {
            return tcis_print;
        }

        public void setTcis_print(String tcis_print) {
            this.tcis_print = tcis_print;
        }

        public String getCd_method() {
            return cd_method;
        }

        public void setCd_method(String cd_method) {
            this.cd_method = cd_method;
        }

        public String getDis_zk() {
            return dis_zk;
        }

        public void setDis_zk(String dis_zk) {
            this.dis_zk = dis_zk;
        }

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public int getIsVerifing() {
            return isVerifing;
        }

        public void setIsVerifing(int isVerifing) {
            this.isVerifing = isVerifing;
        }
    }
}
