package com.cndll.chgj.mvp.mode;


import com.cndll.chgj.mvp.mode.bean.request.RequestAddCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetStoreList;
import com.cndll.chgj.mvp.mode.bean.request.RequestHomeInfo;
import com.cndll.chgj.mvp.mode.bean.request.RequestLogin;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestRegister;
import com.cndll.chgj.mvp.mode.bean.request.RequestVerify;
import com.cndll.chgj.mvp.mode.bean.request.RequsetHomeMendianList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseAddCaipin;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.mode.bean.response.ResponseCailei;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStoreList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseHome;
import com.cndll.chgj.mvp.mode.bean.response.ResponseLogin;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseRegister;
import com.cndll.chgj.mvp.mode.bean.response.ResponseStoreTye;
import com.cndll.chgj.mvp.mode.bean.response.ResponseVerify;

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

    @GET("Store/getstlist")
    Observable<ResponseStoreTye> getStoreType();

    @POST("User/getverify")
    Observable<ResponseVerify> getVerify(@Body RequestVerify requestVerify);

    @POST("/User/reg ")
    Observable<ResponseRegister> register(@Body RequestRegister requestRegister);

    @POST("User/login")
    Observable<ResponseLogin> login(@Body RequestLogin requestLogin);

    @POST("Store/getstorelist")
    Observable<ResponseGetStoreList> getStoreList(@Body RequestGetStoreList requestGetStoreList);

    @POST("Dish/adddc")
    Observable<ResponseCailei> addCailei(@Body RequestAddCailei requestAddCailei);

    @POST("Print/prlist")
    Observable<ResponsePrintList> getPrintList(@Body RequestPrintList requestPrintList);

    @POST("Dish/adddish")
    Observable<ResponseAddCaipin> addCaipinList(@Body ResponseAddCaipin responseAddCaipin);

    @POST("Dish/getdishlist")
    Observable<ResponseGetCaipinList> getCaipinList(@Body RequestGetCaipinList requestGetCaipinList);

    @POST("Dish/getdclist")
    Observable<ResponseGetCaileiList> getCaileiList(@Body RequestPrintList requestGetCaipinList);

    @POST("Dish/adddc")
    Observable<ResponseCailei> AddCailei(@Body RequestAddCailei requestGetCaipinList);

}
