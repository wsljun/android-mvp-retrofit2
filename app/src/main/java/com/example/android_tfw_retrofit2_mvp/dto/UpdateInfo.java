package com.example.android_tfw_retrofit2_mvp.dto;

/**
 * Created by 李均 on 2016/11/24.
 */

public class UpdateInfo {


    /**
     * state : 1
     * message : 获取最新版本信息成功
     * version_no : 2.1.18
     * version_name : 提分王安卓Pad V2.1.18
     * isforce : 1
     * haslatest : 1
     * file_name : wiselearn_pad_V2.1.18.apk
     * comment : 1.设置信息中省会信息完善。
     * downlaod_page : http://down.huixueyuan.com/publish_cs/android_wiselearn.html
     * file_path : http://down.huixueyuan.com/publish_cs/wiselearn/wiselearn_pad_V2.1.18.apk
     */

    private int state;
    private String message;
    private String version_no;
    private String version_name;
    private int isforce;
    private int haslatest;
    private String file_name;
    private String comment;
    private String downlaod_page;
    private String file_path;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion_no() {
        return version_no;
    }

    public void setVersion_no(String version_no) {
        this.version_no = version_no;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public int getIsforce() {
        return isforce;
    }

    public void setIsforce(int isforce) {
        this.isforce = isforce;
    }

    public int getHaslatest() {
        return haslatest;
    }

    public void setHaslatest(int haslatest) {
        this.haslatest = haslatest;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDownlaod_page() {
        return downlaod_page;
    }

    public void setDownlaod_page(String downlaod_page) {
        this.downlaod_page = downlaod_page;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
