package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.view.RegisterView;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface RegisterPresenter extends BasePresenter<RegisterView> {
    void register(String storeId, String storeType, String tel, String storeName, String address);

    void getArea();

    void getVerify(String tel);

    void getStoreType();
}
