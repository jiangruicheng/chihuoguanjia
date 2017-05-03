package com.cndll.chgj.mvp.mode.bean.response;

import com.cndll.chgj.adapter.DataList;

import java.util.List;

/**
 * Created by kongqing on 2017/5/2.
 */

public class ResponseGetStoreList extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"3","name":"乡村18碗","code":"732820","province":"广东","city":"深圳","type":"湘菜馆","ord":"3"},{"id":"28","name":"xxx","code":"278850","province":"1","city":"44","type":"春灌","ord":"26"},{"id":"38","name":"龙岗香菜馆","code":"734510","province":"2","city":"500","type":"香菜馆1","ord":"27"}]
     * extra : 获取成功
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends DataList {
        /**
         * id : 3
         * name : 乡村18碗
         * code : 732820
         * province : 广东
         * city : 深圳
         * type : 湘菜馆
         * ord : 3
         */

        private String id;
        private String name;
        private String code;
        private String province;
        private String city;
        private String type;
        private String ord;

        @Override
        public int getOrderList() {
            return Integer.valueOf(ord);
        }

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getOrd() {
            return ord;
        }

        public void setOrd(String ord) {
            this.ord = ord;
        }

        @Override
        public String getIDList() {
            return id;
        }

        @Override
        public void setOrderList(int i) {
            ord = String.valueOf(i);
        }
    }
}
