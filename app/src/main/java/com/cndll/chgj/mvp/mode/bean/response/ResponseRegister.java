package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/4/26.
 */

public class ResponseRegister extends BaseResponse {

    /**
     * code : 1
     * data : {"code":"773290","tel":"15001372759","password":null}
     * extra : 注册成功
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
         * code : 773290
         * tel : 15001372759
         * password : null
         */

        private String code;
        private String tel;
        private String password;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
