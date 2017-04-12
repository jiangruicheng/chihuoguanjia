package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.request.RequestHomeInfo;
import com.cndll.chgj.mvp.mode.bean.response.ResponseHome;
import com.cndll.chgj.mvp.presenter.HomePresenter;
import com.cndll.chgj.mvp.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/4/12.
 */

public class HomeImpl implements HomePresenter {
    @Override
    public void getHomeInfo() {
        AppRequest.getAPI().getHomeInfo(new RequestHomeInfo().setMid(3).setUid(3)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseHome>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(ResponseHome responseHome) {
                List<String> list = new ArrayList<String>();
                for (ResponseHome.DataBean.BlistBean r : responseHome.getData().getBlist()) {
                    list.add(r.getImgsrc());
                }
                view.setBanner(list);
            }
        });
    }

    HomeView view;

    @Override
    public void setView(HomeView view) {
        this.view = view;
    }
}
