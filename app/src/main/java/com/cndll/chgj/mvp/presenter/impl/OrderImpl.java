package com.cndll.chgj.mvp.presenter.impl;

import com.cndll.chgj.RXbus.EventType;
import com.cndll.chgj.RXbus.RxBus;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseAddOrd;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetOrder;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetSeting;
import com.cndll.chgj.mvp.presenter.OrderPresenter;
import com.cndll.chgj.mvp.view.OrderView;

import java.util.Calendar;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/5/4.
 */

public class OrderImpl implements OrderPresenter {
    OrderView view;

    @Override
    public void setView(OrderView view) {
        this.view = view;
    }

    @Override
    public void getDcList(RequestPrintList requestGetCaileiList) {
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
                            view.setDcList(((ResponseGetCaileiList) baseResponse).getData());

                        }
                    }
                });
    }

    @Override
    public void getDeshList(RequestGetCaipinList requestGetCaipinList) {
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
                            view.setDeshList(((ResponseGetCaipinList) baseResponse).getData()
                            );
                        }
                    }
                });
    }

    @Override
    public void sendOrder(RequestOrder order) {
        AppRequest.getAPI().
                sendOrd(order).
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
                        if (baseResponse.getCode() == 1) {
                            view.sendSucc(((ResponseAddOrd) baseResponse).getData().getOid());
                            getOrder(new RequestGetOrder().setId(((ResponseAddOrd) baseResponse).getData().getOid()));
                            // view.setDeshList(((ResponseGetCaipinList) baseResponse).getData());
                        }
                    }
                });
    }

    @Override
    public void printSetting(final int ord) {
        view.showProg("");
        AppRequest.getAPI().getSetting(AppMode.getInstance().getUid(),
                AppMode.getInstance().getMid()).
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
                        int type;
                        if (baseResponse.getCode() == 1) {
                            ResponseGetSeting responseGetSeting = ((ResponseGetSeting) baseResponse);
                            if (responseGetSeting.getData().getCd_method().equals("1")) {
                                type = 2;
                            } else {
                                type = 2;
                            }
                            printOrders(ord, type);
                        }
                    }
                });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void printOrders(final int ord, int type) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        AppRequest.getAPI().printOrder(ord + "", type + "", year + "-" + month + "-" + day, AppMode.getInstance().getUsername())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(view) {
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
                view.printNoDeskOrderSucc(ord);
                RxBus.getDefault().post(new EventType().setType(EventType.SHOW).setExtra(baseResponse.getExtra()));
                view.disProg();
            }
        });
    }

    @Override
    public void updateOreder(final RequestOrder order) {
        AppRequest.getAPI().
                updateOrd(order).
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
                        if (baseResponse.getCode() == 1) {
                            view.sendSucc(Integer.valueOf(order.getId()));
                            //   view.showMesg("更新成功");
                            getOrder(new RequestGetOrder().setId(Integer.valueOf(order.getId())));
                            // view.setDeshList(((ResponseGetCaipinList) baseResponse).getData());
                        } else {
                            view.disProg();
                            view.showMesg(baseResponse.getExtra());
                        }
                    }
                });
    }

    @Override
    public void getOrder(RequestGetOrder order) {
        AppRequest.getAPI().
                getOrd(order).
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
                    public void onNext(final BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        if (baseResponse.getCode() == 1) {
                            view.setOrder((ResponseGetOrder) baseResponse);
                            // view.setDeshList(((ResponseGetCaipinList) baseResponse).getData());
                        }
                    }
                });
    }

    @Override
    public void removeOrder(final String id, String type) {
        AppRequest.getAPI().
                removerOrder(id, type).
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
                        if (baseResponse.getCode() == 1) {
                            view.sendSucc(0);
                            view.showMesg("更新成功");
                            getOrder(new RequestGetOrder().setId(Integer.valueOf(id)));
                            // view.setDeshList(((ResponseGetCaipinList) baseResponse).getData());
                        }
                    }
                });
    }

    @Override
    public void turnOrder(final String id, String tabname, String tab_id) {
        AppRequest.getAPI().
                turnOrder(id, tabname, tab_id).
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
                        if (baseResponse.getCode() == 1) {
                            view.sendSucc(((ResponseAddOrd) baseResponse).getData().getOid());
                            view.showMesg("更新成功");
                            getOrder(new RequestGetOrder().setId(Integer.valueOf(id)));
                            // view.setDeshList(((ResponseGetCaipinList) baseResponse).getData());
                        }
                    }
                });
    }
}
