package com.cndll.chgj.mvp.presenter;

import android.content.Context;

import com.cndll.chgj.mvp.mode.bean.request.RequestLogin;
import com.cndll.chgj.mvp.view.LoginView;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface LoginPresenter extends BasePresenter<LoginView> {
    void login(RequestLogin requestLogin, Context context);
}
