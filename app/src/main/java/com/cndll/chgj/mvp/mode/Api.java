package com.cndll.chgj.mvp.mode;


import com.cndll.chgj.mvp.mode.bean.request.RequestAddCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddCaipin;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddDesk;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddPrint;
import com.cndll.chgj.mvp.mode.bean.request.RequestAddStaff;
import com.cndll.chgj.mvp.mode.bean.request.RequestDelete;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteCaipin;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestDeleteStaff;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetBillList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetDeskList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetMethodList;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestGetStoreList;
import com.cndll.chgj.mvp.mode.bean.request.RequestHomeInfo;
import com.cndll.chgj.mvp.mode.bean.request.RequestLogin;
import com.cndll.chgj.mvp.mode.bean.request.RequestMendianOrd;
import com.cndll.chgj.mvp.mode.bean.request.RequestOrder;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintBackDesh;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintBill;
import com.cndll.chgj.mvp.mode.bean.request.RequestPrintList;
import com.cndll.chgj.mvp.mode.bean.request.RequestQueryAppData;
import com.cndll.chgj.mvp.mode.bean.request.RequestRegister;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpLoadPayInfo;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaCailei;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaCaipin;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdaDesk;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateMethod;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdatePrint;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateStaff;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpdateStoery;
import com.cndll.chgj.mvp.mode.bean.request.RequestVerify;
import com.cndll.chgj.mvp.mode.bean.request.RequsetHomeMendianList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseAddOrd;
import com.cndll.chgj.mvp.mode.bean.response.ResponseAddPrint;
import com.cndll.chgj.mvp.mode.bean.response.ResponseAppRecord;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.mode.bean.response.ResponseBank;
import com.cndll.chgj.mvp.mode.bean.response.ResponseCailei;
import com.cndll.chgj.mvp.mode.bean.response.ResponseDeletePrint;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetBillList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaileiList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetCaipinList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetDeskList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetOrder;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetSeting;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStaffList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseGetStoreList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseHome;
import com.cndll.chgj.mvp.mode.bean.response.ResponseLogin;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseMethod;
import com.cndll.chgj.mvp.mode.bean.response.ResponseOrd;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePayStatue;
import com.cndll.chgj.mvp.mode.bean.response.ResponsePrintList;
import com.cndll.chgj.mvp.mode.bean.response.ResponseQueryAppData;
import com.cndll.chgj.mvp.mode.bean.response.ResponseRecord;
import com.cndll.chgj.mvp.mode.bean.response.ResponseRegister;
import com.cndll.chgj.mvp.mode.bean.response.ResponseStoreTye;
import com.cndll.chgj.mvp.mode.bean.response.ResponseUpdatePrint;
import com.cndll.chgj.mvp.mode.bean.response.ResponseUploadImage;
import com.cndll.chgj.mvp.mode.bean.response.ResponseVerify;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by jiang_ruicheng on 16/10/27.
 */
public interface Api {
    @POST("/Store/upstoreinfo")
    Observable<ResponseCailei> updateStore(@Body RequestUpdateStoery requestUpdateStoery);

    @POST("/System/storechargelist")
    Observable<ResponseAppRecord> getAppRecord(@Body RequestGetMethodList requestGetMethodList);

    @Multipart
    @POST("File/uploadPicture")
    Observable<ResponseUploadImage> uploadImage(@PartMap Map<String, RequestBody> pa);

    @FormUrlEncoded
    @POST("System/storecharge")
    Observable<ResponseRecord> record(@Field("mid") String id);

    @FormUrlEncoded
    @POST("Order/cashpay")
    Observable<ResponseOrd> payMoney(@Field("id") String id,
                                     @Field("ssmoney") String ssmoney,
                                     @Field("paytype") String type
    );

    @FormUrlEncoded
    @POST("System/advsetinfo")
    Observable<ResponseGetSeting> getSetting(@Field("uid") String uid,
                                             @Field("mid") String mid
    );

    @FormUrlEncoded
    @POST("User/modifypass")
    Observable<ResponseOrd> updatePassword(@Field("uid") String uid,
                                           @Field("tel") String tel,
                                           @Field("newpass") String newpass
    );

    @FormUrlEncoded
    @POST("User/modifypass")
    Observable<ResponseOrd> updatePassword(@Field("tel") String tel,
                                           @Field("newpass") String newpass
    );

    @FormUrlEncoded
    @POST("Print/printdish")
    Observable<ResponseCailei> printOrder(@Field("id") String id,
                                          @Field("type") String type,
                                          @Field("date") String data,
                                          @Field("sname") String name);

    @POST("Print/printdish")
    Observable<ResponseCailei> printOrder(@Body RequestPrintBackDesh requestPrintBackDesh);

    @POST("Print/printdish")
    Observable<ResponseCailei> printAddOrder(@Body RequestPrintBill requestPrintBackDesh);

    @FormUrlEncoded
    @POST("Print/printorder")
    Observable<ResponseCailei> printBill(@Field("id") String id,
                                         @Field("printer") String name,
                                         @Field("print_id") String print_id);

    @FormUrlEncoded
    @POST("Print/day_sale_print")
    Observable<ResponseCailei> printDayReport(@Field("uid") String uid,
                                              @Field("mid") String mid,
                                              @Field("stm") String stm,
                                              @Field("etm") String etm,
                                              @Field("print_id") String print_id);

    @FormUrlEncoded
    @POST("Print/dish_sale_print")
    Observable<ResponseCailei> printAllReport(@Field("uid") String uid,
                                              @Field("mid") String mid,
                                              @Field("stm") String stm,
                                              @Field("etm") String etm,
                                              @Field("print_id") String print_id);

    @POST("System/payapply")
    Observable<ResponseCailei> uploadPayInfo(@Body RequestUpLoadPayInfo info);

    @POST("system/getbanklist")
    Observable<ResponseBank> getBankList();

    @FormUrlEncoded
    @POST("System/advset")
    Observable<ResponseCailei> setting(@Field("uid") String uid, @Field("mid") String mid, @Field("tcis_print") String tcis_print, @Field("cd_method") String cd_method, @Field("dis_zk") String dis_zk);

    @FormUrlEncoded
    @POST("user/loginout")
    Observable<ResponseAddOrd> logoff(@Field("uid") String uid);

    @POST("Store/getstoreinfo/")
    Observable<ResponseHome> getHomeInfo(@Body RequestHomeInfo info);

    @POST("Order/orderlists")
    Observable<ResponseGetBillList> getBill(@Body RequestGetBillList info);

    @FormUrlEncoded
    @POST("User/get_applypay_status")
    Observable<ResponsePayStatue> payStatue(@Field("uid") String uid, @Field("mid") String mid);

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

    @POST("System/storechargewarn")
    Observable<ResponseQueryAppData> getAppData(@Body RequestQueryAppData requestQueryAppData);

    @POST("Print/prlist")
    Observable<ResponsePrintList> getPrintList(@Body RequestPrintList requestPrintList);

    @POST("Print/add")
    Observable<ResponseAddPrint> addPrint(@Body RequestAddPrint requestPrintList);

    @POST("Print/printdelete")
    Observable<ResponseDeletePrint> deletePrint(@Body RequestDelete requestPrintList);

    @POST("Print/modify")
    Observable<ResponseUpdatePrint> updatePrint(@Body RequestUpdatePrint requestPrintList);

    @POST("Dish/getdishlist")
    Observable<ResponseGetCaipinList> getCaipinList(@Body RequestGetCaipinList requestGetCaipinList);

    @POST("Dish/getdclist")
    Observable<ResponseGetCaileiList> getCaileiList(@Body RequestPrintList requestGetCaipinList);

    @POST("Dish/adddc")
    Observable<ResponseCailei> AddCailei(@Body RequestAddCailei requestGetCaipinList);

    @POST("Dish/adddish")
    Observable<ResponseCailei> addCaipin(@Body RequestAddCaipin requestGetCaipinList);

    @POST("Dish/updc")
    Observable<ResponseOrd> updateCailei(@Body RequestUpdaCailei requestGetCaipinList);

    @POST("Dish/updish")
    Observable<ResponseOrd> updateCaipin(@Body RequestUpdaCaipin requestGetCaipinList);

    @POST("Dish/deldc")
    Observable<ResponseCailei> deleteCailei(@Body RequestDeleteCailei requestGetCaipinList);

    @POST("Dish/deldish")
    Observable<ResponseCailei> deleteCaipin(@Body RequestDeleteCaipin requestGetCaipinList);

    @POST("store/multisort")
    Observable<ResponseOrd> ordMendian(@Body List<RequestMendianOrd> list);

    @POST("Table/multisort")
    Observable<ResponseOrd> ordDesk(@Body List<RequestMendianOrd> list);

    @POST("Dish/multisort")
    Observable<ResponseOrd> ordCaipin(@Body List<RequestMendianOrd> list);

    @POST("Dish/dc_multisort")
    Observable<ResponseOrd> ordCailei(@Body List<RequestMendianOrd> list);

    @POST("dish/dm_multisort")
    Observable<ResponseOrd> ordMethod(@Body List<RequestMendianOrd> list);

    @POST("Dish/adddishmethod")
    Observable<ResponseCailei> addMethod(@Body RequestAddMethod requestAddMethod);

    @POST("Dish/deldishmethod")
    Observable<ResponseCailei> deletMethod(@Body RequestDeleteMethod requestAddMethod);

    @POST("Dish/updishmethod")
    Observable<ResponseCailei> updateMethod(@Body RequestUpdateMethod requestAddMethod);

    @POST("Dish/getdishmethodlist")
    Observable<ResponseMethod> getMethodList(@Body RequestGetMethodList requestAddMethod);

    @POST("table/addtable")
    Observable<ResponseCailei> addDesk(@Body RequestAddDesk requestAddCailei);

    @POST("table/uptableord")
    Observable<ResponseCailei> updatDesk(@Body RequestUpdaDesk requestAddCailei);

    @POST("table/deltable")
    Observable<ResponseCailei> deleteDesk(@Body RequestDelete requestAddCailei);

    @POST("table/gettablelist")
    Observable<ResponseGetDeskList> getDeskList(@Body RequestGetDeskList requestAddCailei);

    @POST("order/addorder")
    Observable<ResponseAddOrd> sendOrd(@Body RequestOrder requestAddCailei);

    @FormUrlEncoded
    @POST("order/modifyorder")
    Observable<ResponseAddOrd> removerOrder(@Field("id") String id, @Field("type") String type,@Field("payee") String name);

    @FormUrlEncoded
    @POST("order/modifyorder")
    Observable<ResponseAddOrd> updataPayee(@Field("id") String id, @Field("payee") String payee);

    @FormUrlEncoded
    @POST("order/modifyorder")
    Observable<ResponseAddOrd> turnOrder(@Field("id") String id, @Field("tabname") String tabname, @Field("tab_id") String tab_id);

    @POST("order/modifyorder ")
    Observable<ResponseOrd> updateOrd(@Body RequestOrder requestAddCailei);

    @POST("order/orderdetail")
    Observable<ResponseGetOrder> getOrd(@Body RequestGetOrder requestAddCailei);

    @POST("Staff/add")
    Observable<ResponseAddPrint> addStaff(@Body RequestAddStaff requestAddCailei);

    @POST("Staff/modify")
    Observable<ResponseAddPrint> updateStaff(@Body RequestUpdateStaff requestAddCailei);

    @POST("Staff/del")
    Observable<ResponseAddPrint> deleteStaff(@Body RequestDeleteStaff requestAddCailei);

    @POST("Staff/getlist")
    Observable<ResponseGetStaffList> getStaffList(@Body RequestPrintList requestAddCailei);

}
