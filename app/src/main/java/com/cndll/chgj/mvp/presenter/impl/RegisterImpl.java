package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetStoreList;
import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.mode.bean.request.RequestRegister;
import com.cndll.chgj.mvp.mode.bean.request.RequestVerify;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStoreList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseRegister;
import com.cndll.chgj.mvp.mode.bean.response.ResponseStoreTye;
import com.cndll.chgj.mvp.mode.bean.response.ResponseVerify;
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
    public void register(String storeType, String tel, String storeName, int province, int city) {
        AppRequest.getAPI().register(new RequestRegister().setCity(city).
                setName(storeName).setProvince(province).
                setTel(tel).setType(storeType)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    view.showRegisterInfo((ResponseRegister) baseResponse);
                    getStoreList(AppMode.getInstance().getUid());
                }
            }
        });
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

    public String getVerify() {
        return verify;
    }

    private String verify = null;

    @Override
    public void getVerify(String tel) {
        AppRequest.getAPI().getVerify(new RequestVerify().setTel(tel)).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showMesg("获取失败");
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse instanceof ResponseVerify) {
                    if (baseResponse.getCode() == 1) {
                        verify = String.valueOf(((ResponseVerify) baseResponse).getData());
                        view.showMesg("验证码已发送成功");
                        view.setVerify(String.valueOf(((ResponseVerify) baseResponse).getData()));
                    }

                }
            }
        });
    }

    @Override
    public void getStoreType() {
        AppRequest.getAPI().getStoreType().subscribeOn(Schedulers.io()).
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
                    view.showStoreType(((ResponseStoreTye) baseResponse).getData());
                }
            }
        });
    }

    @Override
    public void getStoreList(String uid) {
        AppRequest.getAPI().getStoreList(new RequestGetStoreList().setUid(uid)).subscribeOn(Schedulers.io()).
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
                    view.loadListView(((ResponseGetStoreList) baseResponse).getData());
                }
            }
        });
    }

    @Override
    public void ordStore(List<RequestMendianOrd> list) {
        AppRequest.getAPI().ordMendian(list).
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
                        view.showMesg(baseResponse.getExtra());
                    }
                });
    }
}
