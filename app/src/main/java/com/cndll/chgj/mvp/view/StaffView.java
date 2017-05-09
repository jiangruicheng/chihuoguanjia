package com.cndll.chgj.mvp.view;

import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStaffList;
import com.cndll.chgj.mvp.presenter.StaffPresenter;

import java.util.List;

/**
 * Created by kongqing on 2017/5/8.
 */

public interface StaffView extends BaseView<StaffPresenter> {
    void showStaffList(List<ResponseGetStaffList.DataBean> responseAddStaff);
}
