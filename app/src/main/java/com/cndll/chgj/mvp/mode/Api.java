package com.cndll.chgj.mvp.mode;


        import com.cndll.chgj.mvp.mode.bean.request.RequestAddCailei;
        import com.cndll.chgj.mvp.mode.bean.request.RequestHomeInfo;
        import com.cndll.chgj.mvp.mode.bean.request.RequestLogin;
        import com.cndll.chgj.mvp.mode.bean.request.RequestRegister;
        import com.cndll.chgj.mvp.mode.bean.request.RequestVerify;
        import com.cndll.chgj.mvp.mode.bean.request.RequsetHomeMendianList;
        import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
        import com.cndll.chgj.mvp.mode.bean.response.ResponseCailei;
        import com.cndll.chgj.mvp.mode.bean.response.ResponseHome;
        import com.cndll.chgj.mvp.mode.bean.response.ResponseLogin;
        import com.cndll.chgj.mvp.mode.bean.response.ResponseMendianHomeList;
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

    Observable<ResponseRegister> register(@Body RequestRegister requestRegister);

    @POST("User/login")
    Observable<ResponseLogin> login(@Body RequestLogin requestLogin);

    @POST("Dish/adddc")
    Observable<ResponseCailei> addCailei(@Body RequestAddCailei requestAddCailei);
}
