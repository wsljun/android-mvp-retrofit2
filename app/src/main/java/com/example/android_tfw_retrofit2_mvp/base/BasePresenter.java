package com.example.android_tfw_retrofit2_mvp.base;

public interface BasePresenter extends BaseCallBackListener {

    void start(); // 此方法主要用于activity / frgment 中创建presenter 之后初始化数据使用

}
