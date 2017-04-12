package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.presenter.LoginPresenter;
import com.cndll.chgj.mvp.view.LoginView;

/**
 * Created by kongqing on 2017/4/11.
 */

public class LoginImpl implements LoginPresenter {
    LoginView view;

    @Override
    public void setView(LoginView view) {
        this.view = view;
    }

    @Override
    public void login(String user, String password, String storeId) {
        view.loginSucces();
    }
}
