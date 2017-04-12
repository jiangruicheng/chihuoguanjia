package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.presenter.HomePresenter;

import java.util.List;

/**
 * Created by kongqing on 2017/4/12.
 */

public interface HomeView extends BaseView<HomePresenter> {
    void setBanner(List<String> urls);
}
