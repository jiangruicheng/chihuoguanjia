package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.view.BaseView;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface BasePresenter<T extends BaseView> {

    void setView(T view);
}
