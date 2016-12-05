package com.example.android_tfw_retrofit2_mvp.utils;

import android.os.Environment;

/**
 * Created by huangzhiwei on 15/4/18.
 */
public class Constants {

    public static String ENVIROMENT_DIR_CACHE = Environment
            .getExternalStorageDirectory() + "/student_android_phone/";

    public static String CURRENT_XUEDUAN = "";
    //初中标志  xueduan
    public static final String CHUZHONG = "2";
    //高中标志  xueduan
    public static final String GAOZHONG = "3";


     public static String APP_DB_DIRNAME = "report/cache";

    public static final String CC_USERID = "44F259E17B57C74B";
    public static final String CC_KEY = "Xn8X9HBCWl3YZue8YGJbfUlXnqW8jb56";

    public static final String[] months = new String[]{"月","01","02","03","04","05","06","07","08","09","10","11","12"};
    public static final String[] days = new String[]{"日","01","02","03","04","05","06","07","08","09","10",
                                                          "11","12","13","14","15","16","17","18","19","20",
                                                          "21","22","23","24","25","26","27","28","29","30","31"
                                                          };
    public static final String[] days30 = new String[]{"日","01","02","03","04","05","06","07","08","09","10",
                                                          "11","12","13","14","15","16","17","18","19","20",
                                                          "21","22","23","24","25","26","27","28","29","30"
                                                          };
    public static final String[] days28 = new String[]{"日","01","02","03","04","05","06","07","08","09","10",
                                                          "11","12","13","14","15","16","17","18","19","20",
                                                          "21","22","23","24","25","26","27","28"
                                                          };
    public static final String[] days29 = new String[]{"日","01","02","03","04","05","06","07","08","09","10",
                                                          "11","12","13","14","15","16","17","18","19","20",
                                                          "21","22","23","24","25","26","27","28","29"
                                                          };
    /**
     * 标记档案馆报告类型
     * */
    public static int DANGANGUAN_REPORT_TAG = 0;

    public static final  int ZHENDUAN_REPORT_TAG = 1; //  诊断报告  1
    public static final  int TASK_REPORT_TAG = 2;   //   任务塔报告 2

    /**
     *  用户协议静态页面
     * */
    public static final String USER_AGREEMENT_HTLM="http://api.huixueyuan.cn/ifdood_dev01/v2/link/yonghuxieyi.html";

    /**
     *  ("apptype", "4"); //设备类型 4->代表提分王PAD 6 -> 提分王手机
     * */
    public static final String APPTYPE = "4";

    /**
     * ("usertype", "1");//手机端传2，pad端传1
     * */

    public static final String USERTYPE = "1";

    /**
     *  String key  用来临时标记线上版本号
     * */

    public static final String TEMP_VERSION_KEY ="TEMP_VERSION";

    public static final String ACTION_UPDATE = "com.tifenwang.update";

    public static final String ANSWER_FILENAME = "answer_map";//题目信息文件存储路径

    public static boolean UPDATEING = false;//是否正在更新

    public static boolean RIGHTANSWER = false;//是否显示正确答案

    public static final String SINGLE = "1";//普通的单选题
    public static final String BLANK = "4";//普通的填空题
    public static final String ALL_BLANK = "13";//大题中的单选或填空题

    // MIME_MapTable是所有文件的后缀名所对应的MIME类型的一个String数组：
    public static final String[][] MIME_MapTable={
            //{后缀名，MIME类型}
            {".3gp",    "video/3gpp"},
            {".apk",    "application/vnd.android.package-archive"},
            {".asf",    "video/x-ms-asf"},
            {".avi",    "video/x-msvideo"},
            {".bin",    "application/octet-stream"},
            {".bmp",    "image/bmp"},
            {".c",  "text/plain"},
            {".class",  "application/octet-stream"},
            {".conf",   "text/plain"},
            {".cpp",    "text/plain"},
            {".doc",    "application/msword"},
            {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls",    "application/vnd.ms-excel"},
            {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe",    "application/octet-stream"},
            {".gif",    "image/gif"},
            {".gtar",   "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h",  "text/plain"},
            {".htm",    "text/html"},
            {".html",   "text/html"},
            {".jar",    "application/java-archive"},
            {".java",   "text/plain"},
            {".jpeg",   "image/jpeg"},
            {".jpg",    "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log",    "text/plain"},
            {".m3u",    "audio/x-mpegurl"},
            {".m4a",    "audio/mp4a-latm"},
            {".m4b",    "audio/mp4a-latm"},
            {".m4p",    "audio/mp4a-latm"},
            {".m4u",    "video/vnd.mpegurl"},
            {".m4v",    "video/x-m4v"},
            {".mov",    "video/quicktime"},
            {".mp2",    "audio/x-mpeg"},
            {".mp3",    "audio/x-mpeg"},
            {".mp4",    "video/mp4"},
            {".mpc",    "application/vnd.mpohun.certificate"},
            {".mpe",    "video/mpeg"},
            {".mpeg",   "video/mpeg"},
            {".mpg",    "video/mpeg"},
            {".mpg4",   "video/mp4"},
            {".mpga",   "audio/mpeg"},
            {".msg",    "application/vnd.ms-outlook"},
            {".ogg",    "audio/ogg"},
            {".pdf",    "application/pdf"},
            {".png",    "image/png"},
            {".pps",    "application/vnd.ms-powerpoint"},
            {".ppt",    "application/vnd.ms-powerpoint"},
            {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop",   "text/plain"},
            {".rc", "text/plain"},
            {".rmvb",   "audio/x-pn-realaudio"},
            {".rtf",    "application/rtf"},
            {".sh", "text/plain"},
            {".tar",    "application/x-tar"},
            {".tgz",    "application/x-compressed"},
            {".txt",    "text/plain"},
            {".wav",    "audio/x-wav"},
            {".wma",    "audio/x-ms-wma"},
            {".wmv",    "audio/x-ms-wmv"},
            {".wps",    "application/vnd.ms-works"},
            {".xml",    "text/plain"},
            {".z",  "application/x-compress"},
            {".zip",    "application/x-zip-compressed"},
            {"",        "*/*"}
    };
}
