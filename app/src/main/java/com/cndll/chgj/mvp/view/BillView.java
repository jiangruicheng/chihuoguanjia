package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseGetBillList;
import com.cndll.chgj.mvp.presenter.BIllPresenter;

import java.util.List;

/**
 * Created by kongqing on 2017/5/15.
 */

public interface BillView extends BaseView<BIllPresenter> {
    void showBillList(List<ResponseGetBillList.DataBean> dataBeen);
}
