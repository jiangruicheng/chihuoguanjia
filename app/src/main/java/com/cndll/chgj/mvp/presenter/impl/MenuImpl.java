package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.presenter.MenuPrenster;
import com.cndll.chgj.mvp.view.MenuView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/5/2.
 */

public class MenuImpl implements MenuPrenster {
    MenuView view;

    @Override
    public void setView(MenuView view) {
        this.view = view;
    }

    @Override
    public void getPrintList(String uid, String mid) {
        AppRequest.getAPI().getPrintList(new RequestPrintList().setMid(mid).setUid(uid)).
                observeOn(Schedulers.io()).
                subscribeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                if (baseResponse.getCode() == 1) {
                    view.showPrintList(((ResponsePrintList) baseResponse).getData());
                }
            }
        });
    }
}
