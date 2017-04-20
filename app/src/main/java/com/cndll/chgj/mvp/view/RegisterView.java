package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.presenter.RegisterPresenter;

import java.util.List;

/**
 * Created by kongqing on 2017/4/11.
 */

public interface RegisterView extends BaseView<RegisterPresenter> {
    void loadListView();

    void showArea(List<String> item0, List<List<String>> item1, ResponseArea responseArea);
}
