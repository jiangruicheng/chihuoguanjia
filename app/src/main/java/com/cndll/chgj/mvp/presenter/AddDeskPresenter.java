package com.cndll.chgj.mvp.presenter;

import com.cndll.chgj.mvp.mode.bean.request.RequestAddDesk;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetDeskList;
import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaDesk;
import com.cndll.chgj.mvp.view.AddDeskView;

import java.util.List;

/**
 * Created by kongqing on 2017/5/5.
 */

public interface AddDeskPresenter extends BasePresenter<AddDeskView> {
    void getDeskList(RequestGetDeskList requestGetDeskList);

    void addDesk(RequestAddDesk requestAddDesk);

    void deleteDesk(RequestDelete requestDelete);

    void updateDesk(RequestUpdaDesk requestUpdaDesk);

    void ordDesk(List<RequestMendianOrd> list);
}
