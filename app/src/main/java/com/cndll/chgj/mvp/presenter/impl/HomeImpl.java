package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestHomeInfo;
import com.cndll.chgj.mvp.mode.bean.request.RequsetHomeMendianList;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseHome;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;
import com.cndll.chgj.mvp.presenter.HomePresenter;
import com.cndll.chgj.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/4/12.
 */

public class HomeImpl implements HomePresenter {
    @Override
    public void getHomeInfo() {
        AppRequest.getAPI().getHomeInfo(new RequestHomeInfo().
                setMid(AppMode.getInstance().getMid()).
                setUid(AppMode.getInstance().getUid())).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MObeserver(view) {
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
                        if (baseResponse instanceof ResponseHome) {
                            if (((ResponseHome) baseResponse).getCode() == 1) {
                                List<String> list = new ArrayList<String>();
                                for (ResponseHome.DataBean.BlistBean r : ((ResponseHome) baseResponse).getData().getBlist()) {
                                    list.add(r.getImgsrc());
                                }
                                view.setBanner(list);
                                view.setMendianNumb(((ResponseHome) baseResponse).getData().getCode());
                                view.setMonthComin(((ResponseHome) baseResponse).getData().getMonthincome());
                                view.setTodayComin(((ResponseHome) baseResponse).getData().getTodayincome() + "");
                                view.setUserNumb(((ResponseHome) baseResponse).getData().getTel() + "");
                                view.setTodaynumb(((ResponseHome) baseResponse).getData().getTodayordernum() + "");
                                view.setMendianName(((ResponseHome) baseResponse).getData().getName());
                            }
                        }
                    }
                });
    }

    HomeView view;

    @Override
    public void getMendianList() {
        view.showProg("");
        AppRequest.getAPI().getHomeMendianList(new RequsetHomeMendianList().setUid(AppMode.getInstance().getUid())).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MObeserver(view) {
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
                        if (baseResponse instanceof ResponseMendianHomeList) {
                            if (baseResponse.getCode() == 1) {
                                view.setMendianList(((ResponseMendianHomeList) baseResponse).getData());

                            }
                        }
                    }
                });
    }

    @Override
    public void setView(HomeView view) {
        this.view = view;
    }
}
