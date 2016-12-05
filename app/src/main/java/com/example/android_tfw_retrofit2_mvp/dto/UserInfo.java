package com.example.android_tfw_retrofit2_mvp.dto;

import java.util.List;

/**
 * Created by 李均 on 2016/11/14.
 */

public class UserInfo {

    /**
     * user_id : 28113
     * xueduan : 3
     * city : 151
     * school_id : null
     * sound_effect : 1
     * music : 0
     * animation : 1
     * type : 11
     * student_name : wsljun
     * student_phone : 13945678910
     * qq : 929028379
     * parent_phone : 13945678910
     * power : ["1","2","3","4","5","6","7","8","9"]
     * userpower : []
     * success : 1
     * message : 成功
     * limitDay : 0
     * defaultIsSet : 0
     */

    private String user_id;
    private String xueduan;
    private String city;
    private Object school_id;
    private String sound_effect;
    private String music;
    private String animation;
    private String type;
    private String student_name;
    private String student_phone;
    private String qq;
    private String parent_phone;
    private int success;
    private String message;
    private int limitDay;
    private int defaultIsSet;
    private List<String> power;
    private List<String> userpower;

    private  String sId;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getXueduan() {
        return xueduan;
    }

    public void setXueduan(String xueduan) {
        this.xueduan = xueduan;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Object school_id) {
        this.school_id = school_id;
    }

    public String getSound_effect() {
        return sound_effect;
    }

    public void setSound_effect(String sound_effect) {
        this.sound_effect = sound_effect;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setParent_phone(String parent_phone) {
        this.parent_phone = parent_phone;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(int limitDay) {
        this.limitDay = limitDay;
    }

    public int getDefaultIsSet() {
        return defaultIsSet;
    }

    public void setDefaultIsSet(int defaultIsSet) {
        this.defaultIsSet = defaultIsSet;
    }

    public List<String> getPower() {
        return power;
    }

    public void setPower(List<String> power) {
        this.power = power;
    }

    public List<String> getUserpower() {
        return userpower;
    }

    public void setUserpower(List<String> userpower) {
        this.userpower = userpower;
    }
}
