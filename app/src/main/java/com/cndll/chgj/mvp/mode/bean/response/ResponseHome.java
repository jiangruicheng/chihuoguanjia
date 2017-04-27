package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/4/12.
 */

public class ResponseHome extends BaseResponse {


    /**
     * code : 1
     * data : {"id":"3","name":"乡村18碗","code":"732820","type":"湘菜馆","province":"广东","city":"深圳","cre_tm":"1490682839","uid":"3","over_tm":"2088-02-25","tel":"15220163615","todayincome":"0万","todayordernum":"0","monthincome":"0万","blist":[{"url":"http://baidu.com","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"},{"url":"http://sina.com","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"}]}
     * extra : 获取成功
     */

    private DataBean data;



    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public static class DataBean {
        /**
         * id : 3
         * name : 乡村18碗
         * code : 732820
         * type : 湘菜馆
         * province : 广东
         * city : 深圳
         * cre_tm : 1490682839
         * uid : 3
         * over_tm : 2088-02-25
         * tel : 15220163615
         * todayincome : 0万
         * todayordernum : 0
         * monthincome : 0万
         * blist : [{"url":"http://baidu.com","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"},{"url":"http://sina.com","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"}]
         */

        private String id;
        private String name;
        private String code;
        private String type;
        private String province;
        private String city;
        private String cre_tm;
        private String uid;
        private String over_tm;
        private String tel;
        private String todayincome;
        private String todayordernum;
        private String monthincome;
        private List<BlistBean> blist;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getCre_tm() {
            return cre_tm;
        }

        public void setCre_tm(String cre_tm) {
            this.cre_tm = cre_tm;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOver_tm() {
            return over_tm;
        }

        public void setOver_tm(String over_tm) {
            this.over_tm = over_tm;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTodayincome() {
            return todayincome;
        }

        public void setTodayincome(String todayincome) {
            this.todayincome = todayincome;
        }

        public String getTodayordernum() {
            return todayordernum;
        }

        public void setTodayordernum(String todayordernum) {
            this.todayordernum = todayordernum;
        }

        public String getMonthincome() {
            return monthincome;
        }

        public void setMonthincome(String monthincome) {
            this.monthincome = monthincome;
        }

        public List<BlistBean> getBlist() {
            return blist;
        }

        public void setBlist(List<BlistBean> blist) {
            this.blist = blist;
        }

        public static class BlistBean {
            /**
             * url : http://baidu.com
             * imgsrc : http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png
             */

            private String url;
            private String imgsrc;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }
        }
    }
}
