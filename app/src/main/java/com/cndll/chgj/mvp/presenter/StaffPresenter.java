package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestAddStaff;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateStaff;
import com.cndll.chgj.mvp.view.StaffView;

/**
 * Created by kongqing on 2017/5/8.
 */

public interface StaffPresenter extends BasePresenter<StaffView> {
    void addStaff(RequestAddStaff addStaff);


    void updateStaff(RequestUpdateStaff requestAddCailei);


    void deleteStaff(RequestDelete requestAddCailei);


    void getStaffList(RequestPrintList requestAddCailei);
}
