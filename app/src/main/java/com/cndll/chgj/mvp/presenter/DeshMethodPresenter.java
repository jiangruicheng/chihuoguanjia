package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestAddMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetMethodList;
import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateMethod;
import com.cndll.chgj.mvp.view.DeshMethodView;

import java.util.List;

/**
 * Created by kongqing on 2017/5/4.
 */

public interface DeshMethodPresenter extends BasePresenter<DeshMethodView> {
    void getDeshMethodList(RequestGetMethodList requestGetMethodList);

    void addDeshMethod(RequestAddMethod requestAddMethod);

    void deleteDeshMethod(RequestDeleteMethod requestDeleteMethod);

    void updateDeshMethod(RequestUpdateMethod requestUpdateMethod);

    void ordMethod(List<RequestMendianOrd> list);
}
