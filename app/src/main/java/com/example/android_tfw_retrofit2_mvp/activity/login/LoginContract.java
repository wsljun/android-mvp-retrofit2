package com.example.android_tfw_retrofit2_mvp.activity.login;

import com.example.android_tfw_retrofit2_mvp.base.BasePresenter;
import com.example.android_tfw_retrofit2_mvp.base.BaseView;

/**
 * Created by 李均 on 2016/11/14.
 * Contract 约定，更加清晰得管理view 和 presenter
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
         void toMianActivity();

    }

    interface Presenter extends BasePresenter {

        void toLogin(String username, String password);

        void getSubjectInfo();

        void getSid(String userId);

    }

}
