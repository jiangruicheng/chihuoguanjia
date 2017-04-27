package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/4/26.
 */

public class ResponseStoreTye extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"1","name":"香菜管"},{"id":"2","name":"土匪馆"}]
     * extra : 获取成功
     */


    private List<DataBean> data;

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
         * id : 1
         * name : 香菜管
         */

        private String id;
        private String name;

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
    }
}
