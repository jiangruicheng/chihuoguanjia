package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/4/14.
 */

public class ResponseMendianHomeList extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"27","name":"美女如云","code":"236410","province":"6","city":"76","type":"香菜管"},{"id":"26","name":"沙井","code":"672500","province":"2","city":"500","type":"香菜管"},{"id":"25","name":"沙井","code":"924470","province":"2","city":"500","type":"香菜管"},{"id":"24","name":"沙井","code":"358210","province":"2","city":"500","type":"香菜管"},{"id":"23","name":"sj","code":"778950","province":"2","city":"500","type":"香菜管"},{"id":"22","name":"沙井","code":"105600","province":"2","city":"500","type":"香菜管"},{"id":"21","name":"假期","code":"160500","province":"2","city":"500","type":"香菜管"},{"id":"20","name":"假期","code":"620970","province":"2","city":"500","type":"香菜管"},{"id":"19","name":"假期","code":"460880","province":"2","city":"500","type":"香菜管"},{"id":"18","name":"假期","code":"341600","province":"2","city":"500","type":"香菜管"},{"id":"17","name":"沙井","code":"277110","province":"2","city":"500","type":"香菜管"},{"id":"16","name":"沙井","code":"934620","province":"2","city":"500","type":"香菜管"},{"id":"15","name":"沙井","code":"033360","province":"2","city":"501","type":"香菜管"},{"id":"14","name":"沙井","code":"789120","province":"2","city":"500","type":"香菜管"},{"id":"13","name":"沙井","code":"873650","province":"2","city":"500","type":"香菜管"},{"id":"12","name":"沙井","code":"152800","province":"2","city":"500","type":"香菜管"},{"id":"11","name":"沙井","code":"941820","province":"3","city":"36","type":"香菜管"},{"id":"10","name":"嗨","code":"121850","province":"3","city":"38","type":"香菜管"},{"id":"9","name":"沙井","code":"317010","province":"4","city":"53","type":"香菜管"},{"id":"8","name":"沙井","code":"336710","province":"5","city":"62","type":"香菜管"},{"id":"7","name":"沙井","code":"059790","province":"3","city":"36","type":"香菜管"},{"id":"6","name":"沙井","code":"048520","province":"4","city":"53","type":"香菜管"},{"id":"5","name":"沙井","code":"163450","province":"4","city":"53","type":"香菜管"},{"id":"4","name":"沙井","code":"391620","province":"6","city":"77","type":"香菜管"},{"id":"3","name":"乡村18碗","code":"732820","province":"广东","city":"深圳","type":"湘菜馆"}]
     * extra : 获取成功
     */


     List<DataBean> data;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 27
         * name : 美女如云
         * code : 236410
         * province : 6
         * city : 76
         * type : 香菜管
         */

        private String id;
        private String name;
        private String code;
        private String province;
        private String city;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public DataBean setName(String name) {
            this.name = name;
            return this;
        }

        public String getCode() {
            return code;
        }

        public DataBean setCode(String code) {
            this.code = code;
            return this;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
