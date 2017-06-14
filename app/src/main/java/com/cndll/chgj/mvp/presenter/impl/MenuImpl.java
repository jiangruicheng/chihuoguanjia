package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddCaipin;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteCaipin;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaCaipin;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.presenter.MenuPresenter;
import com.cndll.chgj.mvp.view.MenuView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/5/2.
 */

public class MenuImpl implements MenuPresenter {
    MenuView view;

    @Override
    public void setView(MenuView view) {
        this.view = view;
    }

    @Override
    public void getPrintList(String uid, String mid) {
        view.showProg("");
        AppRequest.getAPI().getPrintList(new RequestPrintList().setMid(mid).setUid(uid)).
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
                    view.showPrintList(((ResponsePrintList) baseResponse).getData());

                }
            }
        });
    }

    @Override
    public void getCaileiList(RequestPrintList requestGetCaileiList) {
        view.showProg("");
        AppRequest.getAPI().
                getCaileiList(requestGetCaileiList).
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
                        if (baseResponse.getCode() == 1) {
                            view.setCaileiList(((ResponseGetCaileiList) baseResponse).getData());
                        }
                    }
                });
    }

    @Override
    public void getCaipinList(RequestGetCaipinList requestGetCaipinList) {
        view.showProg("");
        AppRequest.getAPI().
                getCaipinList(requestGetCaipinList).
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
                        view.disProg();
                        if (baseResponse.getCode() == 1) {
                            view.setCaipinList(((ResponseGetCaipinList) baseResponse).getData()
                            );
                        }
                    }
                });
    }


    @Override
    public void addCailei(RequestAddCailei addCailei) {
        AppRequest.getAPI().addCailei(addCailei).
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
                        getCaileiList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                    }
                });
    }

    @Override
    public void addCaipin(RequestAddCaipin addCaipin) {
        AppRequest.getAPI().addCaipin(addCaipin).
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
                        view.updataCaiPinList();
                    }
                });
    }

    @Override
    public void updataCailei(RequestUpdaCailei requestUpdaCailei) {
        AppRequest.getAPI().updateCailei(requestUpdaCailei).
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
                        getCaileiList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                    }
                });
    }

    @Override
    public void updataCaipin(RequestUpdaCaipin requestUpdaCaipin) {
        AppRequest.getAPI().updateCaipin(requestUpdaCaipin).
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
                        view.updataCaiPinList();
                    }
                });
    }

    @Override
    public void deleteCaipin(RequestDeleteCaipin requestDeleteCaipin) {
        AppRequest.getAPI().deleteCaipin(requestDeleteCaipin).
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
                        view.updataCaiPinList();
                    }
                });
    }

    @Override
    public void deleteCailei(RequestDeleteCailei requestDeleteCailei) {
        AppRequest.getAPI().deleteCailei(requestDeleteCailei).
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
                        getCaileiList(new RequestPrintList().setMid(AppMode.getInstance().getMid()).setUid(AppMode.getInstance().getUid()));
                    }
                });
    }

    @Override
    public void ordCailei(List<RequestMendianOrd> list) {
        AppRequest.getAPI().ordCailei(list).
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

    @Override
    public void ordCaipin(List<RequestMendianOrd> list) {
        AppRequest.getAPI().ordCaipin(list).
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
