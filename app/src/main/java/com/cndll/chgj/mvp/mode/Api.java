package com.cndll.chgj.mvp.mode;


import com.cndll.chgj.mvp.mode.bean.request.RequestHomeInfo;
import com.cndll.chgj.mvp.mode.bean.request.RequsetHomeMendianList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.mode.bean.response.ResponseHome;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jiang_ruicheng on 16/10/27.
 */
public interface Api {
    @POST("Store/getstoreinfo/")
    Observable<ResponseHome> getHomeInfo(@Body RequestHomeInfo info);

    @POST("Store/getstorelist")
    Observable<ResponseMendianHomeList> getHomeMendianList(@Body RequsetHomeMendianList info);

    @GET("Area/getarealist")
    Observable<ResponseArea> getArea();
}
