package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/5/8.
 */

public class ResponseGetStaffList extends BaseResponse {

    /**
     * code : 1
     * data : [{"id":"15","tel":"15220163616","password":"11b082df9cbe9cf4c78f159aa3d52d0b","status":"1","cre_tm":"1492429369","over_tm":null,"login_status":"0","username":"小杨的员工","pid":"3","mid":"3","passtext":"123456","token":"7adcd0955742ac9b6d7577660bfccdf3","is_first_login":"0","is_test":"0","qx":"1,2,3,4","qxtxt":"打折,赠送,退菜,结账"}]
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
         * id : 15
         * tel : 15220163616
         * password : 11b082df9cbe9cf4c78f159aa3d52d0b
         * status : 1
         * cre_tm : 1492429369
         * over_tm : null
         * login_status : 0
         * username : 小杨的员工
         * pid : 3
         * mid : 3
         * passtext : 123456
         * token : 7adcd0955742ac9b6d7577660bfccdf3
         * is_first_login : 0
         * is_test : 0
         * qx : 1,2,3,4
         * qxtxt : 打折,赠送,退菜,结账
         */

        private String id;
        private String tel;
        private String password;
        private String status;
        private String cre_tm;
        private Object over_tm;
        private String login_status;
        private String username;
        private String pid;
        private String mid;
        private String passtext;
        private String token;
        private String is_first_login;
        private String is_test;
        private String qx;
        private String qxtxt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public Object getOver_tm() {
            return over_tm;
        }

        public void setOver_tm(Object over_tm) {
            this.over_tm = over_tm;
        }

        public String getLogin_status() {
            return login_status;
        }

        public void setLogin_status(String login_status) {
            this.login_status = login_status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getPasstext() {
            return passtext;
        }

        public void setPasstext(String passtext) {
            this.passtext = passtext;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIs_first_login() {
            return is_first_login;
        }

        public void setIs_first_login(String is_first_login) {
            this.is_first_login = is_first_login;
        }

        public String getIs_test() {
            return is_test;
        }

        public void setIs_test(String is_test) {
            this.is_test = is_test;
        }

        public String getQx() {
            return qx;
        }

        public void setQx(String qx) {
            this.qx = qx;
        }

        public String getQxtxt() {
            return qxtxt;
        }

        public void setQxtxt(String qxtxt) {
            this.qxtxt = qxtxt;
        }
    }
}
