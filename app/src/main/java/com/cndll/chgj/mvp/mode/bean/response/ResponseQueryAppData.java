package com.cndll.chgj.mvp.mode.bean.response;

/**
 * Created by kongqing on 2017/6/13.
 */

public class ResponseQueryAppData extends BaseResponse {

    /**
     * code : 1
     * data : {"isover":1}
     * extra :
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
         * isover : 1
         */

        private int isover;

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        private String days;

        public int getIsover() {
            return isover;
        }

        public void setIsover(int isover) {
            this.isover = isover;
        }
    }
}
