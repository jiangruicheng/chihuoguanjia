package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.presenter.RegisterPresenter;
import com.cndll.chgj.mvp.view.RegisterView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/4/17.
 */

public class RegisterImpl implements RegisterPresenter {
    RegisterView view;

    @Override
    public void setView(RegisterView view) {
        this.view = view;
    }

    @Override
    public void register(String storeId, String storeType, String tel, String storeName, String address) {

    }


    private ArrayList<String> item0 = new ArrayList<>();
    private List<List<String>> item1 = new ArrayList<>();
    private ArrayList<String> item1_ = null;

    @Override
    public void getArea() {
        AppRequest.getAPI().getArea().map(new Func1<ResponseArea, ResponseArea>() {
            @Override
            public ResponseArea call(ResponseArea responseArea) {
                List<ResponseArea.DataBean> counAddresses = responseArea.getData();
                for (int i = 0; i < counAddresses.size(); i++) {
                    item0.add(counAddresses.get(i).getName());
                    item1_ = new ArrayList<String>();
                    for (int j = 0; j < counAddresses.get(i).getCitys().size(); j++) {
                        item1_.add(counAddresses.get(i).getCitys().get(j).getName());
                    }
                    item1.add(item1_);
                }

                return responseArea;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseArea>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ResponseArea o) {
                        view.showArea(item0, item1, o);
                    }
                });
    }
}
