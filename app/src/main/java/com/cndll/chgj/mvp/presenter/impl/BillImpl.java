package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetBillList;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetBillList;
import com.cndll.chgj.mvp.presenter.BIllPresenter;
import com.cndll.chgj.mvp.view.BillView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/5/15.
 */

public class BillImpl implements BIllPresenter {
    BillView view;

    @Override
    public void setView(BillView view) {
        this.view = view;
    }

    @Override
    public void getBillList(RequestGetBillList billList) {
        view.showProg("");
        AppRequest.getAPI().getBill(billList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showBillList(null);
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                view.disProg();
                super.onNext(baseResponse);
                if (baseResponse.getCode() == 1) {
                    view.showBillList(((ResponseGetBillList) baseResponse).getData());

                }
            }
        });
    }
}
