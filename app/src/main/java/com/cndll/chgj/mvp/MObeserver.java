package com.cndll.chgj.mvp;

import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.view.BaseView;

import rx.Observer;

/**
 * Created by kongqing on 2017/4/27.
 */

public class MObeserver implements Observer<BaseResponse> {
    BaseView view;

    public MObeserver(BaseView v) {
        view = v;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(BaseResponse baseResponse) {
        //view.showMesg(baseResponse.getExtra());
        if (baseResponse.getCode() != 1 && view != null) {
            view.showMesg(baseResponse.getExtra());
        }
    }
}
