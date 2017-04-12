package com.cndll.chgj.mvp.mode.bean.response;

import java.util.List;

/**
 * Created by kongqing on 2017/4/12.
 */

public class ResponseHome {

    /**
     * code : 1
     * data : {"name":"老乡店(宝安店)","code":"54845454","tel":null,"todayincome":123,"todayordernum":123,"monthincome":"5.3万","blist":[{"url":"","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"},{"url":"","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"}]}
     * extra : 获取成功
     */

    private int code;
    private DataBean data;
    private String extra;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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
         * name : 老乡店(宝安店)
         * code : 54845454
         * tel : null
         * todayincome : 123
         * todayordernum : 123
         * monthincome : 5.3万
         * blist : [{"url":"","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"},{"url":"","imgsrc":"http://dc.idc.zhonxing.com/Uploads/Picture/2017-03-30/58dccc1ed2d9f.png"}]
         */

        private String name;
        private String code;
        private Object tel;
        private int todayincome;
        private int todayordernum;
        private String monthincome;
        private List<BlistBean> blist;

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

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public int getTodayincome() {
            return todayincome;
        }

        public void setTodayincome(int todayincome) {
            this.todayincome = todayincome;
        }

        public int getTodayordernum() {
            return todayordernum;
        }

        public void setTodayordernum(int todayordernum) {
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
             * url :
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
