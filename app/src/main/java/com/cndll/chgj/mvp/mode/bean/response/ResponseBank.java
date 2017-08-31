package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/5/16.
 */

public class ResponseBank extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * parent_bank_no : 563
         * parent_bank_name : 三井住友银行
         */

        private String parent_bank_no;
        private String parent_bank_name;

        public String getParent_bank_no() {
            return parent_bank_no;
        }

        public void setParent_bank_no(String parent_bank_no) {
            this.parent_bank_no = parent_bank_no;
        }

        public String getParent_bank_name() {
            return parent_bank_name;
        }

        public void setParent_bank_name(String parent_bank_name) {
            this.parent_bank_name = parent_bank_name;
        }
    }
}
