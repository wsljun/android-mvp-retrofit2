package com.example.android_tfw_retrofit2_mvp.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by 李均 on 2016/11/8.
 */

public class LoggingInterceptor implements Interceptor {

    int tag =0;
    public LoggingInterceptor(int tag) {
        this.tag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request.newBuilder().tag(tag);
        Log.d("request_url",request.url().encodedPath()+"; "+request.body().toString());
        long t1 = System.nanoTime();
        Log.d("request_headers",request.headers().toString());
//        logger.info(String.format("Sending request %s on %s%n%s",
//                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
//        logger.info(String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
