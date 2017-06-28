package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetOrder;
import com.cndll.chgj.mvp.presenter.OrderPresenter;
import com.cndll.chgj.weight.MesgShow;

import java.util.List;

/**
 * Created by kongqing on 2017/5/4.
 */

public interface OrderView extends BaseView<OrderPresenter> {
    void setDcList(List<ResponseGetCaileiList.DataBean> data);

    void setDeshList(List<ResponseGetCaipinList.DataBean> deshList);

    void sendSucc(int ord);

    void setOrder(ResponseGetOrder getOrder);
     void showMesgView(String mesg, MesgShow.OnButtonListener sure);
    void printNoDeskOrderSucc(int ord);
    void backView();
}
