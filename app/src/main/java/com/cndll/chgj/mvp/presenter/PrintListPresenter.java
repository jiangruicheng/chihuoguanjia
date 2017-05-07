package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestAddPrint;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdatePrint;
import com.cndll.chgj.mvp.view.PrintView;

/**
 * Created by jiang_ruicheng on 17/5/7.
 */

public interface PrintListPresenter extends BasePresenter<PrintView> {
    void getPrintList(RequestPrintList requestPrintList);

    void addPrint(RequestAddPrint requestAddPrint);

    void updatePrint(RequestUpdatePrint updatePrint);

    void deletePrint(RequestDelete deletePrint);
}
