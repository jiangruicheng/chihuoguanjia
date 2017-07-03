package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddDesk;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetDeskList;
import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaDesk;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetDeskList;
import com.cndll.chgj.mvp.presenter.AddDeskPresenter;
import com.cndll.chgj.mvp.view.AddDeskView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/5/5.
 */

public class AddDeskImpl implements AddDeskPresenter {
    AddDeskView view;

    @Override
    public void setView(AddDeskView view) {
        this.view = view;
    }

    @Override
    public void getDeskList(RequestGetDeskList requestGetDeskList) {
        view.showProg("");
        AppRequest.getAPI().getDeskList(requestGetDeskList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    view.showDeskList(((ResponseGetDeskList) baseResponse).getData());

                }
            }
        });
    }

    @Override
    public void addDesk(RequestAddDesk requestAddDesk) {
        AppRequest.getAPI().addDesk(requestAddDesk).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    view.toast("添加成功");
                    getDeskList(new RequestGetDeskList().setUid(AppMode.getInstance().getUid()).setMid(AppMode.getInstance().getMid()));
                }
            }
        });
    }

    @Override
    public void deleteDesk(RequestDelete requestDelete) {
        AppRequest.getAPI().deleteDesk(requestDelete).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    getDeskList(new RequestGetDeskList().setUid(AppMode.getInstance().getUid()).setMid(AppMode.getInstance().getMid()));
                }
            }
        });
    }

    @Override
    public void updateDesk(RequestUpdaDesk requestUpdaDesk) {
        AppRequest.getAPI().updatDesk(requestUpdaDesk).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                    getDeskList(new RequestGetDeskList().setUid(AppMode.getInstance().getUid()).setMid(AppMode.getInstance().getMid()));
                }
            }
        });
    }

    @Override
    public void ordDesk(List<RequestMendianOrd> list) {
        AppRequest.getAPI().ordDesk(list).
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
