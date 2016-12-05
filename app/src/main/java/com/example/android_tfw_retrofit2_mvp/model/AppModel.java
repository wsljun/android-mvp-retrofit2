package com.example.android_tfw_retrofit2_mvp.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.android_tfw_retrofit2_mvp.api.ApiResponse;
import com.example.android_tfw_retrofit2_mvp.base.BaseCallBackListener;
import com.example.android_tfw_retrofit2_mvp.api.ApiServices;
import com.example.android_tfw_retrofit2_mvp.api.ApiConfing;
import com.example.android_tfw_retrofit2_mvp.api.AppClient;
import com.example.android_tfw_retrofit2_mvp.dto.UserInfo;
import com.google.gson.Gson;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by 李均 on 2016/11/14.
 */

public class AppModel implements Callback<String>{
    /**
     * Retrofit 处理网络请求；
     * */
    private static BaseCallBackListener mBaseCallBackListener;
    private Context mContext;
    private DataServices dataServices; // 用于解析参数
    private  int mRequestTag;
    public AppModel(Context context,BaseCallBackListener baseCallBackListener){
        this.mContext = context;
        this.mBaseCallBackListener = baseCallBackListener;

        dataServices = new DataServices();
    }

    /**
     * @param jsonStr "json"->""
     */
    public void getData(@NonNull String jsonStr , final int requestTag){
        mRequestTag = requestTag;
        ApiConfing.CURRENT_PATH = ApiConfing.UrlPath.getPath(requestTag);
        ApiServices api = AppClient.getRetrofit().create(ApiServices.class);
        api.getData(ApiConfing.CURRENT_PATH,jsonStr)
                .enqueue(this) ;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
                String resultJosn = response.body();
                ApiResponse apiResponse = dataServices.getApiResponse(resultJosn,mRequestTag);
                if(apiResponse.isSuccess()){
                    mBaseCallBackListener.onSuccess(apiResponse,mRequestTag);
                }else{
                    mBaseCallBackListener.onFailed("返回数据异常",mRequestTag);
                }
        }else{
            mBaseCallBackListener.onFailed("respone==null",mRequestTag);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        mBaseCallBackListener.onFailed("请求失败 APPModel",mRequestTag);
    }
}
