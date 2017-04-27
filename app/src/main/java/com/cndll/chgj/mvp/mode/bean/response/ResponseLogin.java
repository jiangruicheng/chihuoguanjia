package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/4/26.
 */

public class ResponseLogin extends BaseResponse {

    /**
     * code : 1
     * data : {"uid":"21","mid":"32","mdname":"龙岗香菜馆","username":"","isboss":1,"qx":"","token":"ee3b7073d50204d94a37f7f9e965abb6","code":"129440"}
     * extra : 登陆成功
     */

    private DataBean data;




    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public static class DataBean {
        /**
         * uid : 21
         * mid : 32
         * mdname : 龙岗香菜馆
         * username :
         * isboss : 1
         * qx :
         * token : ee3b7073d50204d94a37f7f9e965abb6
         * code : 129440
         */

        private String uid;
        private String mid;
        private String mdname;
        private String username;
        private int isboss;
        private String qx;
        private String token;
        private String code;

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

        public String getMdname() {
            return mdname;
        }

        public void setMdname(String mdname) {
            this.mdname = mdname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getIsboss() {
            return isboss;
        }

        public void setIsboss(int isboss) {
            this.isboss = isboss;
        }

        public String getQx() {
            return qx;
        }

        public void setQx(String qx) {
            this.qx = qx;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
