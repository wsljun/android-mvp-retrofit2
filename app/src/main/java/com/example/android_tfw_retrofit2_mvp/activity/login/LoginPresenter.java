package com.example.android_tfw_retrofit2_mvp.activity.login;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.example.android_tfw_retrofit2_mvp.api.ApiResponse;
import com.example.android_tfw_retrofit2_mvp.base.BaseActivity;
import com.example.android_tfw_retrofit2_mvp.dto.UpdateInfo;
import com.example.android_tfw_retrofit2_mvp.dto.UserInfo;
import com.example.android_tfw_retrofit2_mvp.model.AppModel;
import com.example.android_tfw_retrofit2_mvp.api.ApiConfing;
import com.example.android_tfw_retrofit2_mvp.model.RequestTag;
import com.example.android_tfw_retrofit2_mvp.model.security.MD5Util;
import com.example.android_tfw_retrofit2_mvp.utils.JsonParser;
import com.example.android_tfw_retrofit2_mvp.utils.ToastUtil;
import com.example.android_tfw_retrofit2_mvp.utils.Utils;
import com.example.android_tfw_retrofit2_mvp.utils.ValueStorage;
import com.example.android_tfw_retrofit2_mvp.utils.down.UpdateManager;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MediaType;

/**
 * Created by 李均 on 2016/11/14.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private Context mContext;
    @NonNull
    private LoginContract.View loginView;
    @NonNull
    private AppModel appModel;
    @NonNull
    String deviceID; // 设备Id
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public  String version_name;
    public String mUserName,mPassWord;
    public String mGrade; // 年级
    private String userID;


    public LoginPresenter(Context mContext, LoginContract.View loginView) {
        this.mContext = mContext;
        this.loginView = loginView;
        appModel = new AppModel(mContext,this);
        loginView.setPresenter(this);
        deviceID = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        start();
    }


    @Override
    public void toLogin(String username, String password) {
        mUserName = username;
        mPassWord = password;
        appModel.getData(getJsonParams(username,password), RequestTag.LOGIN);
    }

    @Override
    public void getSubjectInfo() {

    }

    @Override
    public void getSid(String userId ) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ip", Utils.getLocalIpAddress());
        map.put("mac", Utils.getLocalMacAddress(mContext));
        map.put("userID", userId);
        map.put("sid", userId);
        map.put("type", "app");
        appModel.getData(JsonParser.convertJson(map),RequestTag.CHECKMAC);
    }

    @Override
    public void start() {
        // :2016/11/16  在此处检查更新
        version_name = UpdateManager.getVerName(mContext);
        HashMap<String,Object> map = new HashMap<>();
        map.put("version", version_name);
        map.put("type","4");
        appModel.getData(JsonParser.convertJson(map),RequestTag.CHECKUPDATE);
    }

    /**
     * lj 返回json字符串
     *
     * @param un
     * @param pd
     * @return
     */
    public String getJsonParams( String un, String pd) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("username", un);
            jo.put("password", pd);
            jo.put("usertype", "2");//pad端传1,手机端传2，
            jo.put("userId", "");   // userId && channelId 需要绑定百度成功后才能得到数据，第一次登陆传 空字符串
            jo.put("channelId", "");
            jo.put("terminal", "a"); // a 代表安卓
            jo.put("model", deviceID); //获取手机型号
            jo.put("release", Build.VERSION.SDK_INT + ","
                    + Build.VERSION.RELEASE);//RELEASE获取版本号
            //这里针对比较特殊的传值方式  需要添加一个md5值加密
            jo.put("mk", MD5Util.md5(ApiConfing.MD5String));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

    /**
     * */
    @Override
    public void onSuccess(ApiResponse apiResponse, int taskid) {
        switch (taskid){
            case RequestTag.LOGIN:
                UserInfo userInfo = (UserInfo) apiResponse.getObj();
                mGrade = userInfo.getXueduan();
                userID = userInfo.getUser_id();
                ValueStorage.put("username",mUserName);
                ValueStorage.put("password",mPassWord);
                ValueStorage.put("user_type",userInfo.getType());
                ValueStorage.put("xueduan",mGrade);
                ValueStorage.put("city",userInfo.getCity());
                ValueStorage.put("userID",userID);
                // 登陆验证
                getSid(userID);
                break;
            case RequestTag.CHECKUPDATE:
                try {
                    UpdateInfo dto = (UpdateInfo) apiResponse.getObj();
                    if (dto.getHaslatest() == 1) {//有更新
                        if(version_name.compareTo(dto.getVersion_no())<0){
                            ToastUtil.showMessage(mContext,"有更新");
                            UpdateManager updateManager = new UpdateManager(mContext,dto);
                            updateManager.showNoticeDialog();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showMessage(mContext,"信息错误");
                }
                break;
            case RequestTag.CHECKMAC:
                userInfo = (UserInfo) apiResponse.getObj();
                ToastUtil.showMessage(mContext,"CHECKMAC= "+  userInfo.getsId());
                loginView.toMianActivity();
                break;
        }


    }

    @Override
    public void onFailed(Object obj, int taskid) {
        loginView.showErro(taskid,obj.toString());
    }
}
