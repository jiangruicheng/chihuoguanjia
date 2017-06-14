package com.cndll.chgj.util;

import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetSeting;

import java.util.Calendar;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kongqing on 2017/6/14.
 */

public class PrintSendOrder {
    public static void printSetting(final int ord) {
        AppRequest.getAPI().getSetting(AppMode.getInstance().getUid(),
                AppMode.getInstance().getMid()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new MObeserver(null) {
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
    public static void printOrders(int ord, int type) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        AppRequest.getAPI().printOrder(ord + "", type + "", year + "-" + month + "-" + day, AppMode.getInstance().getUsername())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(null) {
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
            }
        });
    }

}
