package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetMethodList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateMethod;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;
import com.cndll.chgj.mvp.presenter.DeshMethodPresenter;
import com.cndll.chgj.mvp.view.DeshMethodView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/5/4.
 */

public class DeshMethodImpl implements DeshMethodPresenter {


    @Override
    public void getDeshMethodList(RequestGetMethodList requestGetMethodList) {
        AppRequest.getAPI().getMethodList(requestGetMethodList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    view.showMethodList(((ResponseMethod) baseResponse).getData());
                }
            }
        });
    }

    @Override
    public void addDeshMethod(RequestAddMethod requestAddMethod) {
        AppRequest.getAPI().addMethod(requestAddMethod).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    getDeshMethodList(new RequestGetMethodList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                }
            }
        });
    }

    @Override
    public void deleteDeshMethod(RequestDeleteMethod requestDeleteMethod) {
        AppRequest.getAPI().deletMethod(requestDeleteMethod).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    getDeshMethodList(new RequestGetMethodList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                }
            }
        });
    }

    @Override
    public void updateDeshMethod(RequestUpdateMethod requestUpdateMethod) {
        AppRequest.getAPI().updateMethod(requestUpdateMethod).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    getDeshMethodList(new RequestGetMethodList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                }
            }
        });
    }

    DeshMethodView view;

    @Override
    public void setView(DeshMethodView view) {
        this.view = view;
    }
}
