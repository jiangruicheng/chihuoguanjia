package com.cndll.chgj.mvp.mode.bean.request;

import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;

import java.util.List;

/**
 * Created by kongqing on 2017/5/9.
 */

public class RequestUpdataDeshItems extends BaseRequest{
    String id;

    public String getId() {
        return id;
    }

    public RequestUpdataDeshItems setId(String id) {
        this.id = id;
        return this;
    }

    public List<ResponseGetCaipinList.DataBean> getItems() {
        return items;
    }

    public RequestUpdataDeshItems setItems(List<ResponseGetCaipinList.DataBean> items) {
        this.items = items;
        return this;
    }

    List<ResponseGetCaipinList.DataBean> items;
}
