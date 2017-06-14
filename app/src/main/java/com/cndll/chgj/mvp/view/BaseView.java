package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.presenter.BasePresenter;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface BaseView<T extends BasePresenter> {
    void showMesg(String mesg);

    void showProg(String mesg);

    void disProg();

    void setPresenter(T presenter);
}
