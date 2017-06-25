package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.view.OrderView;

/**
 * Created by kongqing on 2017/5/4.
 */

public interface OrderPresenter extends BasePresenter<OrderView> {
    static final int GIVE = 1;
    static final int BACK = 2;
    void getDcList(RequestPrintList requestGetCaileiList);

    void getDeshList(RequestGetCaipinList requestGetCaipinList);

    void sendOrder(RequestOrder order);

    void updateOreder(RequestOrder order);

    void updateOreder(RequestOrder order, int type);

    void getOrder(RequestGetOrder order);

    void printSetting(final int ord);

    void removeOrder(String id, String type);

    void turnOrder(String id, String tabname, String tab_id);
}
