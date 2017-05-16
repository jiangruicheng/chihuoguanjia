package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/5/15.
 */

public class ResponseGetBillList extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"284","type":"2","ssmoney":"0.00","tabname":"xhfjdkslsh","ordnum":"2017-05-15-9989","type_txt":"撤台"}]
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
         * id : 284
         * type : 2
         * ssmoney : 0.00
         * tabname : xhfjdkslsh
         * ordnum : 2017-05-15-9989
         * type_txt : 撤台
         */

        private String id;
        private String type;
        private String ssmoney;
        private String tabname;
        private String ordnum;
        private String type_txt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSsmoney() {
            return ssmoney;
        }

        public void setSsmoney(String ssmoney) {
            this.ssmoney = ssmoney;
        }

        public String getTabname() {
            return tabname;
        }

        public void setTabname(String tabname) {
            this.tabname = tabname;
        }

        public String getOrdnum() {
            return ordnum;
        }

        public void setOrdnum(String ordnum) {
            this.ordnum = ordnum;
        }

        public String getType_txt() {
            return type_txt;
        }

        public void setType_txt(String type_txt) {
            this.type_txt = type_txt;
        }
    }
}
