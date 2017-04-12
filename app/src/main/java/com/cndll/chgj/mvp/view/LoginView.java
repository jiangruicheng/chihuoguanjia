package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.presenter.LoginPresenter;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface LoginView extends BaseView<LoginPresenter> {
    void loginSucces();

    void showUserMesg(String[] strings);


}
