package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.view.HomeView;

/**
 * Created by kongqing on 2017/4/12.
 */

public interface HomePresenter extends BasePresenter<HomeView> {
    void getHomeInfo();

    void getMendianList();
}
