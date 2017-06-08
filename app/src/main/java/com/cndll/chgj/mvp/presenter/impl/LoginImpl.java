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

                        String qx = ((ResponseLogin) baseResponse).getData().getQx();
                        if (qx.contains("1")) {
                            AppMode.getInstance().setDiscount(true);
                        } else {
                            AppMode.getInstance().setDiscount(false);
                        }
                        if (qx.contains("2")) {
                            AppMode.getInstance().setGive(true);
                        } else {
                            AppMode.getInstance().setGive(false);
                        }
                        if (qx.contains("3")) {
                            AppMode.getInstance().setReturn(true);
                        } else {
                            AppMode.getInstance().setReturn(false);
                        }
                        if (qx.contains("4")) {
                            AppMode.getInstance().setOrder(true);
                        } else {
                            AppMode.getInstance().setOrder(false);
                        }
                        if (qx.contains("5")) {
                            AppMode.getInstance().setExcel(true);
                        } else {
                            AppMode.getInstance().setExcel(false);
                        }
                        if (((ResponseLogin) baseResponse).getData().getIsboss() == 1) {
                            AppMode.getInstance().setBoss(true);
                        } else {
                            AppMode.getInstance().setBoss(false);
                        }
                        AppMode.getInstance().setLoading(true).
                                setMid(((ResponseLogin) baseResponse).getData().getMid()).
                                setToken(((ResponseLogin) baseResponse).getData().getToken()).
                                setUid(((ResponseLogin) baseResponse).getData().getUid()).
                                setTel(login.getTel()).setUsername(((ResponseLogin) baseResponse).getData().getUsername()).setMcode(((ResponseLogin) baseResponse).getData().getCode());
                        SharedPreferences.Editor editor = context.getSharedPreferences("CHGJ", Context.MODE_PRIVATE).edit();
                        editor.putString("mdname", ((ResponseLogin) baseResponse).getData().getMdname());
                        editor.putString("mdcode", ((ResponseLogin) baseResponse).getData().getCode());
                        editor.putString("tel", login.getTel());
                        editor.putString("mid", AppMode.getInstance().getMid());
                        editor.putString("uid", AppMode.getInstance().getUid());
                        editor.putString("token", AppMode.getInstance().getToken());
                        editor.putString("username", AppMode.getInstance().getUsername());
                        editor.putBoolean("isboss", AppMode.getInstance().isBoss());
                        editor.putBoolean("isdiscount", AppMode.getInstance().isDiscount());
                        editor.putBoolean("isexcel", AppMode.getInstance().isExcel());
                        editor.putBoolean("isorder", AppMode.getInstance().isOrder());
                        editor.putBoolean("isreturn", AppMode.getInstance().isReturn());
                        editor.putBoolean("isgive", AppMode.getInstance().isGive());
                        editor.putBoolean("isloding", true);
                        editor.commit();
                    }
                }
            }
        });
    }
}
