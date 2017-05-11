package com.cndll.chgj.mvp.presenter.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestLogin;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseLogin;
import com.cndll.chgj.mvp.presenter.LoginPresenter;
import com.cndll.chgj.mvp.view.LoginView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public void login(final RequestLogin login, final Context context) {
        AppRequest.getAPI().login(login).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse instanceof ResponseLogin) {
                    if ((baseResponse).getCode() == 1) {
                        view.loginSucces();
                        SharedPreferences.Editor editor = context.getSharedPreferences("CHGJ", Context.MODE_PRIVATE).edit();
                        editor.putString("mdname", ((ResponseLogin) baseResponse).getData().getMdname());
                        editor.putString("mdcode", ((ResponseLogin) baseResponse).getData().getCode());
                        editor.putString("tel", login.getTel());
                        editor.commit();
                        AppMode.getInstance().setLoading(true).
                                setMid(((ResponseLogin) baseResponse).getData().getMid()).
                                setToken(((ResponseLogin) baseResponse).getData().getToken()).
                                setUid(((ResponseLogin) baseResponse).getData().getUid()).
                                setTel(login.getTel());
                    }
                }
            }
        });
    }
}
