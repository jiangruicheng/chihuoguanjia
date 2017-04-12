package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.view.LoginView;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface LoginPresenter extends BasePresenter<LoginView> {
    void login(String user, String password,String storeId);
}
