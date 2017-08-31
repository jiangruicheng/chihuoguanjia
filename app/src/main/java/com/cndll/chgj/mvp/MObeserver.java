package com.cndll.chgj.mvp;

import com.cndll.chgj.RXbus.EventType;
import com.cndll.chgj.RXbus.RxBus;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
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
        // view.showMesg(e.toString());
        e.printStackTrace();
        if (view != null)
            view.showMesg("网络链接错误");
    }

    @Override
    public void onNext(BaseResponse baseResponse) {
        //view.showMesg(baseResponse.getExtra());
        if (baseResponse.getCode() == -211 && view != null) {
            AppMode.getInstance().setMid("3").setMcode("");
            AppMode.getInstance().setUid("3");
            AppMode.getInstance().setUsername("");
            view.showMesg("你的账号在其它设备上登录，已强制下线，请重新登录。");
            AppMode.getInstance().setLoading(false);
            AppMode.getInstance().setToken(null);
            RxBus.getDefault().post(new EventType().setType(EventType.BACKKEY));
            return;
        }
        if ((baseResponse.getCode() != 1 && baseResponse.getCode() != 200) && view != null) {
            view.showMesg(baseResponse.getExtra());
        }
    }
}
