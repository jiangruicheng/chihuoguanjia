package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestAddCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddCaipin;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteCaipin;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaCaipin;
import com.cndll.chgj.mvp.view.MenuView;

/**
 * Created by kongqing on 2017/5/2.
 */

public interface MenuPrenster extends BasePresenter<MenuView> {
    void getPrintList(String uid, String mid);

    void getCaileiList(RequestPrintList requestGetCaileiList);

    void getCaipinList(RequestGetCaipinList requestGetCaipinList);

    void addCailei(RequestAddCailei addCailei);

    void addCaipin(RequestAddCaipin addCaipin);

    void updataCailei(RequestUpdaCailei requestUpdaCailei);

    void updataCaipin(RequestUpdaCaipin requestUpdaCaipin);

    void deleteCaipin(RequestDeleteCaipin requestDeleteCaipin);

    void deleteCailei(RequestDeleteCailei requestDeleteCailei);
}
