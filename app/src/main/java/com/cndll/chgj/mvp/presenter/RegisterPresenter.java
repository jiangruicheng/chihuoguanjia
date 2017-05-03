package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.view.RegisterView;

import java.util.List;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface RegisterPresenter extends BasePresenter<RegisterView> {
    void register(String storeType, String tel, String storeName, int province, int city);

    void getArea();

    void getVerify(String tel);

    void getStoreType();

    void getStoreList(String uid);
    void ordStore(List<RequestMendianOrd> list);
}
