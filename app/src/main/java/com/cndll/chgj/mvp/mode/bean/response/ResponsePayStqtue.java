package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/5/16.
 */

public class ResponsePayStqtue extends BaseResponse {

    /**
     * code : 1
     * data : {"name":"13045913861","password":"hua6802176"}
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
         * name : 13045913861
         * password : hua6802176
         */

        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
