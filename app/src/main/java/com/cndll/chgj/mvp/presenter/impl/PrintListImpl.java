package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddPrint;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdatePrint;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.presenter.PrintListPresenter;
import com.cndll.chgj.mvp.view.PrintView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public class PrintListImpl implements PrintListPresenter {
    @Override
    public void getPrintList(RequestPrintList requestPrintList) {
        AppRequest.getAPI().getPrintList(requestPrintList).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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

    @Override
    public void addPrint(RequestAddPrint requestAddPrint) {
        AppRequest.getAPI().addPrint(requestAddPrint).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                getPrintList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
            }
        });
    }

    @Override
    public void updatePrint(RequestUpdatePrint updatePrint) {
        AppRequest.getAPI().updatePrint(updatePrint).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                getPrintList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
            }
        });

    }

    @Override
    public void deletePrint(RequestDelete deletePrint) {
        AppRequest.getAPI().deletePrint(deletePrint).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                getPrintList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
            }
        });

    }


    private PrintView view;

    @Override
    public void setView(PrintView view) {
        this.view = view;
    }
}
