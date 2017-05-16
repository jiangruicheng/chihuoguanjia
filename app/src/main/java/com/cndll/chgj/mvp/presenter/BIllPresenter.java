package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestGetBillList;
import com.cndll.chgj.mvp.view.BillView;

/**
 * Created by kongqing on 2017/5/15.
 */

public interface BIllPresenter extends BasePresenter<BillView> {
    void getBillList(RequestGetBillList billList);
}
