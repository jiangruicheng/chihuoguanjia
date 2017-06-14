package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddStaff;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteStaff;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateStaff;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStaffList;
import com.cndll.chgj.mvp.presenter.StaffPresenter;
import com.cndll.chgj.mvp.view.StaffView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/5/8.
 */

public class StaffImpl implements StaffPresenter {


    @Override
    public void addStaff(RequestAddStaff addStaff) {
        AppRequest.getAPI().addStaff(addStaff).
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
                    getStaffList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                }
            }
        });
    }

    @Override
    public void updateStaff(RequestUpdateStaff requestAddCailei) {
        AppRequest.getAPI().updateStaff(requestAddCailei).
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
                    getStaffList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                }
            }
        });
    }

    @Override
    public void deleteStaff(RequestDeleteStaff requestAddCailei) {
        AppRequest.getAPI().deleteStaff(requestAddCailei).
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
                    getStaffList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                }
            }
        });
    }

    @Override
    public void getStaffList(RequestPrintList requestAddCailei) {
        view.showProg("");
        AppRequest.getAPI().getStaffList(requestAddCailei).
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
                view.disProg();
                if (baseResponse.getCode() == 1) {
                    view.showStaffList(((ResponseGetStaffList) baseResponse).getData());
                }

            }
        });
    }

    StaffView view;

    @Override
    public void setView(StaffView view) {
        this.view = view;
    }
}
