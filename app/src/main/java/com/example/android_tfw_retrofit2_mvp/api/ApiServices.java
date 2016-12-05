package com.example.android_tfw_retrofit2_mvp.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by 李均 on 2016/11/14.
 * Api 统一接口
 */

public interface ApiServices {

    @FormUrlEncoded
    @POST
    Call<String> getData(@Url String url ,@Field("json") String jsonStr);


    @FormUrlEncoded
    @POST("http://192.168.12.227/ifdood_dev01/users/checkMac.php")  //使用完整Url时　ｂａｓｅｕｒｌ会被忽略
    Call<String> checkMac(@Field("json") String jsonStr);
}
