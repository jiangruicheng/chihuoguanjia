package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.presenter.PrintListPresenter;

import java.util.List;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public interface PrintView extends BaseView<PrintListPresenter> {
    void showPrintList(List<ResponsePrintList.DataBean> dataBeen);
}
