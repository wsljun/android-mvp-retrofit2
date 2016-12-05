package com.example.android_tfw_retrofit2_mvp.api;

import com.example.android_tfw_retrofit2_mvp.model.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 李均 on 2016/11/14.
 * 封装retrofit2框架 作为网络请求
 *
 */

public class AppClient {

    public static Retrofit retrofit;
    public static OkHttpClient okHttp;
    public ApiServices api ;

    private static String apiBaseUrl ="";
    private static  Retrofit.Builder builder = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                   .baseUrl(ApiConfing.BASEURL);


    public static Retrofit getRetrofit() {
        if(null==retrofit){
            synchronized (AppClient.class){
                if(null==retrofit){
                    initRetrofir();
                }
            }
        }
        return retrofit;
    }

    private static void initRetrofir() {
        initOkHttp();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfing.BASEURL)
                .client(okHttp)
//                .addConverterFactory(GsonConverterFactory.create()) //json
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        // api service
//         api = retrofit.create(API.class);
        // 发起请求 ,异步
//        api.getData("").enqueue();

    }

    /**
     * 初始化 okhttp
     */
    private static void initOkHttp(){
        //
//        HttpLoggingInterceptor httpLog = new HttpLoggingInterceptor();
//        httpLog.setLevel(HttpLoggingInterceptor.Level.BODY);
        LoggingInterceptor loggerInterceptor = new LoggingInterceptor(1);
        okHttp = new OkHttpClient.Builder()
                .connectTimeout(ApiConfing.CONNECTTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConfing.WRITETIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApiConfing.READTIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggerInterceptor)
                .build();

    }

    /**
     * 创建 service
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T createServiceFrom(Class<T> serviceClass) {
        return builder.build().create(serviceClass);
    }

    /**
     * 运行时修改 BaseURl
     * @param newApiBaseUrl
     */
    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
    }



}
