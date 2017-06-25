package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by jiangruicheng on 2017/6/25.
 */

public class ResponseAppRecord extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"31","name":"一个月","money":"0.01","month":"1","uid":"3","mid":"3","cre_tm":"1496633866","ordnum":"2017060511372454","type":"2","tm_txt":"2017-06-05 11:37:46"},{"id":"26","name":"1个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1495593335","ordnum":null,"type":null,"tm_txt":"2017-05-24 10:35:35"},{"id":"21","name":"3个月","money":"0.01","month":"3","uid":"3","mid":"3","cre_tm":"1493116060","ordnum":"2017042518272959","type":"2","tm_txt":"2017-04-25 18:27:40"},{"id":"20","name":"3个月","money":"0.01","month":"3","uid":"3","mid":"3","cre_tm":"1493115948","ordnum":"2017042518253398","type":"2","tm_txt":"2017-04-25 18:25:48"},{"id":"19","name":"3个月","money":"0.01","month":"3","uid":"3","mid":"3","cre_tm":"1493114952","ordnum":"2017042518084550","type":"2","tm_txt":"2017-04-25 18:09:12"},{"id":"17","name":"70年权限","money":"0.01","month":"840","uid":"3","mid":"3","cre_tm":"1493095575","ordnum":"2017042512455579","type":"2","tm_txt":"2017-04-25 12:46:15"},{"id":"16","name":"一个月","money":"0.01","month":"840","uid":"3","mid":"3","cre_tm":"1493090464","ordnum":"2017042511202360","type":"2","tm_txt":"2017-04-25 11:21:04"},{"id":"14","name":"一个月","money":"0.01","month":"1","uid":"3","mid":"3","cre_tm":"1493089680","ordnum":"2017042511074185","type":"2","tm_txt":"2017-04-25 11:08:00"},{"id":"11","name":"一个月","money":"0.01","month":"1","uid":"3","mid":"3","cre_tm":"1493021426","ordnum":"2017042416082343","type":"2","tm_txt":"2017-04-24 16:10:26"},{"id":"10","name":"一个月","money":"0.01","month":"1","uid":"3","mid":"3","cre_tm":"1492705285","ordnum":"2017042100200025","type":"1","tm_txt":"2017-04-21 00:21:25"},{"id":"9","name":"一个月","money":"0.01","month":"1","uid":"3","mid":"3","cre_tm":"1492704205","ordnum":"2017042100022919","type":"2","tm_txt":"2017-04-21 00:03:25"},{"id":"8","name":"一个月","money":"0.01","month":"3","uid":"3","mid":"3","cre_tm":"1491979973","ordnum":null,"type":null,"tm_txt":"2017-04-12 14:52:53"},{"id":"7","name":"一个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1491534055","ordnum":null,"type":null,"tm_txt":"2017-04-07 11:00:55"},{"id":"6","name":"一个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1491534049","ordnum":null,"type":null,"tm_txt":"2017-04-07 11:00:49"},{"id":"5","name":"一个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1491533347","ordnum":null,"type":null,"tm_txt":"2017-04-07 10:49:07"},{"id":"4","name":"一个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1491533283","ordnum":null,"type":null,"tm_txt":"2017-04-07 10:48:03"},{"id":"3","name":"一个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1491533257","ordnum":null,"type":null,"tm_txt":"2017-04-07 10:47:37"},{"id":"2","name":"一个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1491533235","ordnum":null,"type":null,"tm_txt":"2017-04-07 10:47:15"},{"id":"1","name":"一个月","money":"50.00","month":"1","uid":"3","mid":"3","cre_tm":"1491533215","ordnum":null,"type":null,"tm_txt":"2017-04-07 10:46:55"}]
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
         * id : 31
         * name : 一个月
         * money : 0.01
         * month : 1
         * uid : 3
         * mid : 3
         * cre_tm : 1496633866
         * ordnum : 2017060511372454
         * type : 2
         * tm_txt : 2017-06-05 11:37:46
         */

        private String id;
        private String name;
        private String money;
        private String month;
        private String uid;
        private String mid;
        private String cre_tm;
        private String ordnum;
        private String type;
        private String tm_txt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
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

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public String getOrdnum() {
            return ordnum;
        }

        public void setOrdnum(String ordnum) {
            this.ordnum = ordnum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTm_txt() {
            return tm_txt;
        }

        public void setTm_txt(String tm_txt) {
            this.tm_txt = tm_txt;
        }
    }
}
