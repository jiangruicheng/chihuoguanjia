package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/5/11.
 */

public class ResponseAddOrd extends BaseResponse {

    /**
     * code : 1
     * data : {"oid":141}
     * extra : 添加成功
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
         * oid : 141
         */

        private int oid;

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }
    }
}
