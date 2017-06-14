package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;
import com.cndll.chgj.mvp.presenter.DeshMethodPresenter;

import java.util.List;

/**
 * Created by kongqing on 2017/5/4.
 */

public interface DeshMethodView extends BaseView<DeshMethodPresenter> {
    void showMethodList(List<ResponseMethod.DataBean> dataBeen);
    void succGetList();
}
