package com.cndll.chgj.mvp.mode.bean.response;

import com.cndll.chgj.adapter.DataList;

import java.util.List;

/**
 * Created by kongqing on 2017/5/2.
 */

public class ResponseGetCaileiList extends BaseResponse {

    /**
     * code : 1
     * data : [{"name":"小炒类","id":"3","ord":"0"},{"name":"小炒类","id":"5","ord":"1"},{"name":"小炒类","id":"18","ord":"2"},{"name":"小炒类","id":"4","ord":"3"}]
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
         * name : 小炒类
         * id : 3
         * ord : 0
         */

        private String name;
        private String id;
        private String ord;

        @Override
        public int getOrderList() {
            return Integer.valueOf(ord);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
