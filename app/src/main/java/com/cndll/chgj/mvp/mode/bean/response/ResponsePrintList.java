package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/5/2.
 */

public class ResponsePrintList extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"2","uid":"3","mid":"3","name":"厨房打印机","code":"817501573","key":"9xv9tf5p","is_default":"1","status":0,"cre_tm":"1490929671","type":"1"},{"id":"3","uid":"3","mid":"3","name":"jiezhang","code":"817501573","key":"9xv9tf5p","is_default":"1","status":0,"cre_tm":"1490929704","type":"2"}]
     * extra : 获取成功
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * uid : 3
         * mid : 3
         * name : 厨房打印机
         * code : 817501573
         * key : 9xv9tf5p
         * is_default : 1
         * status : 0
         * cre_tm : 1490929671
         * type : 1
         */

        private String id;
        private String uid;
        private String mid;
        private String name;
        private String code;
        private String key;
        private String is_default;
        private int status;
        private String cre_tm;
        private String type;

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

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
