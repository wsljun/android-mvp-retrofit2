package com.example.android_tfw_retrofit2_mvp.base;

import com.example.android_tfw_retrofit2_mvp.api.ApiResponse;

/**
 * Created by 李均 on 2016/11/14.
 */

public interface BaseCallBackListener<T> {
    void onSuccess(ApiResponse apiResponse, int taskid);
    void onFailed(Object s,int taskid);
}
