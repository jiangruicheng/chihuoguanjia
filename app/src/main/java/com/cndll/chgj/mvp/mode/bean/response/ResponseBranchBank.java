package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017-08-30.
 */

public class ResponseBranchBank extends BaseResponse {


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
         * bank_name : 三井住友银行(中国)有限公司
         * bank_no : 563290000018
         */

        private String parent_bank_no;
        private String parent_bank_name;
        private String bank_name;
        private String bank_no;

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

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_no() {
            return bank_no;
        }

        public void setBank_no(String bank_no) {
            this.bank_no = bank_no;
        }
    }
}
