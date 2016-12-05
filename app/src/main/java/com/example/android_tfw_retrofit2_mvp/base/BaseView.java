package com.example.android_tfw_retrofit2_mvp.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgress(boolean b);

    void showErro(int a,String msg);

}
