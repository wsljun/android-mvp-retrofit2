package com.example.android_tfw_retrofit2_mvp.model;

import com.example.android_tfw_retrofit2_mvp.api.ApiResponse;
import com.example.android_tfw_retrofit2_mvp.dto.UpdateInfo;
import com.example.android_tfw_retrofit2_mvp.dto.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 李均 on 2016/11/16.
 * 接口返回数据解析类
 */

public class DataParser {

    /**
     * 登陆接口 返回数据解析
     * @param apiResponse
     * @param object
     * @return
     * @throws Exception
     *
     *
    "user_id":"28113",
    "xueduan":"3",
    "city":"110101",
    "school_id":null,
    "sound_effect":"1",
    "music":"1",
    "animation":"1",
    "type":"11",
    "student_name":"wsljun",
    "student_phone":"13945678910",
    "qq":"929028379",
    "parent_phone":"13945678910",
    "power":Array[9],
    "userpower":Array[0],
    "success":1,
    "message":"成功",
    "limitDay":0,
    "defaultIsSet":0
     */
    public static ApiResponse<UserInfo> login(ApiResponse<UserInfo> apiResponse, JSONObject object){
        try {
            UserInfo userInfo = new UserInfo();
            if (object.has("user_id"))
                userInfo.setUser_id(object.getString("user_id"));
            if (object.has("xueduan"))
                userInfo.setXueduan(object.getString("xueduan"));
            if (object.has("type"))
                userInfo.setType(object.getString("type"));
            if (object.has("student_name"))
                userInfo.setStudent_name(object.getString("student_name"));
            if (object.has("student_phone"))
                userInfo.setStudent_phone(object.getString("student_phone"));
            if (object.has("qq"))
                userInfo.setQq(object.getString("qq"));
            if (object.has("parent_phone"))
                userInfo.setParent_phone(object.getString("parent_phone"));
            if (object.has("city"))
                userInfo.setCity(object.getString("city"));



            apiResponse.setObj(userInfo);


        } catch (JSONException e) {
            e.printStackTrace();

        }
        return apiResponse;
    }


    /**
     * 检查更新
     * @param apiResponse
     * @param object
     * @return
     */
    public static  ApiResponse checkUpdate(ApiResponse<UpdateInfo> apiResponse, JSONObject object){
        try {
            UpdateInfo dto = new UpdateInfo();
            if (object.has("version_no"))
                dto.setVersion_no(object.getString("version_no"));
            if (object.has("version_name"))
                dto.setVersion_name(object.getString("version_name"));
            if (object.has("isforce"))
                dto.setIsforce(object.getInt("isforce"));
            if (object.has("haslatest"))
                dto.setHaslatest(object.getInt("haslatest"));
            if (object.has("file_name"))
                dto.setFile_name(object.getString("file_name"));
            if (object.has("comment"))
                dto.setComment(object.getString("comment"));
            if (object.has("file_path"))
                dto.setFile_path(object.getString("file_path"));

            apiResponse.setObj(dto);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  apiResponse;
    }
    // 发送mac地址 {"type":"1","sID":"5ff0e335ab7e1fb9a2819048c7cb90ff"}
    public static ApiResponse checkMac(ApiResponse<UserInfo> apiResponse, JSONObject object) throws JSONException {
        UserInfo userInfo = new UserInfo();
        if("1".equals(object.has("type")?object.getString("sID"):"")){

        }
        if(object.has("sID")){
            userInfo.setsId(object.getString("sID"));
        }
        apiResponse.setObj(userInfo);
        return apiResponse;
    }





}
