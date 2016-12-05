package com.example.android_tfw_retrofit2_mvp.api;

import android.support.annotation.Nullable;

import com.example.android_tfw_retrofit2_mvp.model.RequestTag;

/**
 * Created by 李均 on 2016/11/14.
 */

public class ApiConfing {
    public static  String BASEURL;
    public static  String CURRENT_PATH = "";// 用于动态赋值
    public static  String HTML_SEND_ANWSER = "http://192.168.12.227/ifdood_dev01/"; ;

    public static void initBaseUrl(boolean isdebug) {
        if(isdebug){
            /**
             * 本地测试环境
             */
            BASEURL = "http://192.168.12.227/ifdood_dev01/v2/";//测试接口
        }else{
            // 正式环境
            BASEURL = "http://api.huixueyuan.cn/ifdood_dev01/v2/";//正式接口

        }
    }

    //okhttp 设置网络请求等待读取时间
    public static final int CONNECTTIMEOUT =10;
    public static final int WRITETIMEOUT =10;
    public static final int READTIMEOUT =10;

    //md5加密的字符串
    public static final String MD5String = "4fH1w90sPpIX4z";

    public static class UrlPath{

        public static String getPath(int tag){
            String path;
            switch (tag){

                case RequestTag.LOGIN: //登陆接口
                    path = "xitong/userLogin_app.php";
                    break;

                case RequestTag.CHECKUPDATE: //检查更新
                    path = "xitong/checkUpdate.php";
                    break;
                case RequestTag.CHECKMAC:
                    path =  HTML_SEND_ANWSER+"users/checkMac.php"; //登录验证
                    break;


                default:
                    path ="";
            }

            return path;

        }



    }
}
