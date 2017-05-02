package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.view.MenuView;

/**
 * Created by kongqing on 2017/5/2.
 */

public interface MenuPrenster extends BasePresenter<MenuView> {
    void getPrintList(String uid, String mid);
}
