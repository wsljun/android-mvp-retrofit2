package com.example.android_tfw_retrofit2_mvp.model;

import com.example.android_tfw_retrofit2_mvp.api.ApiResponse;
import com.example.android_tfw_retrofit2_mvp.dto.UpdateInfo;
import com.example.android_tfw_retrofit2_mvp.dto.UserInfo;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 李均 on 2016/11/22.
 */

public class DataServices {
    public static ApiResponse apiResponse;
    String event = "0";
    String msg = "请求错误，请稍后重试！";

    public DataServices() {

    }
    /**
     * 获取ApiResponse
     *
     * @param type 请求类型
     * @param data 服务器返回数据
     * @return 相对应的ApiResponse
     * @throws Exception
     */
    public  ApiResponse getApiResponse(String data,int type)  {
        try {
            JSONObject object = new JSONObject(data);
            if (object.has("success"))
                event = object.getString("success");
            if (object.has("message"))
                msg = object.getString("message");
            if (object.has("state")) // 返回数据没有 success
                event = object.getString("state");

            switch (type){
                case RequestTag.LOGIN:
//                 UserInfo user = new Gson().fromJson(data,UserInfo.class); //返回数据类型为String 字符串。使用Gson进行转换
                    apiResponse =   DataParser.login(new ApiResponse<UserInfo>(event, msg),object);
                    break;
                case RequestTag.CHECKUPDATE: //检查更新
                    apiResponse =   DataParser.checkUpdate(new ApiResponse<UpdateInfo>(event, msg),object);
                    break;
                case RequestTag.CHECKMAC:// 验证登陆
                    apiResponse =   DataParser.checkMac(new ApiResponse<UserInfo>(event, msg),object);
                    break;
                default:
                    apiResponse = null;
                    break;
            }
        }catch (JSONException j) {
            Logger.d("getApiResponse : "+ type+"; ex="+j.toString());
        }
        return  apiResponse ;
    }



}
