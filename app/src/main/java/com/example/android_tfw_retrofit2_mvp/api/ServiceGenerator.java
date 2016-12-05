package com.example.android_tfw_retrofit2_mvp.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李均 on 2016/12/1.
 */

public class ServiceGenerator {
    public static  String apiBaseUrl = "http://futurestud.io/api";
    private static Retrofit retrofit;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(apiBaseUrl);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    // No need to instantiate this class.
    private ServiceGenerator() {

    }

    //    public static <S> S createService(Class<S> serviceClass, AccessToken token) {
//        String authToken = token.getTokenType().concat(token.getAccessToken());
//        return createService(serviceClass, authToken);
//    }
    public static <T> T createServiceFrom(Class<T> serviceClass) {
        return builder.build().create(serviceClass);
    }


    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;

        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
    }


    // more methods
    // ...

}
