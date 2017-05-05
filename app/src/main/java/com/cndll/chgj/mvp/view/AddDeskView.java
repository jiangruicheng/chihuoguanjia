package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseGetDeskList;
import com.cndll.chgj.mvp.presenter.AddDeskPresenter;

import java.util.List;

/**
 * Created by kongqing on 2017/5/5.
 */

public interface AddDeskView extends BaseView<AddDeskPresenter> {
    void showDeskList(List<ResponseGetDeskList.DataBean> dataBeen);
}
