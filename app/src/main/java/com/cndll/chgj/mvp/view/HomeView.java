package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;
import com.cndll.chgj.mvp.presenter.HomePresenter;

import java.util.List;

/**
 * Created by kongqing on 2017/4/12.
 */

public interface HomeView extends BaseView<HomePresenter> {
    void setBanner(List<String> urls);

    void setUserNumb(String s);

    void setMendianNumb(String s);

    void setTodayComin(String s);

    void setTodaynumb(String s);

    void setMonthComin(String s);

    void setMendianName(String s);

    void setMendianList(List<ResponseMendianHomeList.DataBean> list);
}
