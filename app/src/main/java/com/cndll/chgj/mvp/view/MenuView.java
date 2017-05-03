package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.presenter.MenuPrenster;

import java.util.List;

/**
 * Created by kongqing on 2017/5/2.
 */

public interface MenuView extends BaseView<MenuPrenster> {
    void showPrintList(List<ResponsePrintList.DataBean> dataBeen);

    void setCaileiList(List<ResponseGetCaileiList.DataBean> dataBeen);

    void setCaipinList(List<ResponseGetCaipinList.DataBean> dataBeen);

    void updataCaiPinList();

}
