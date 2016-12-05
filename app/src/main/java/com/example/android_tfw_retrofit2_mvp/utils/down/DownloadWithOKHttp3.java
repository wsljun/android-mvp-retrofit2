package com.example.android_tfw_retrofit2_mvp.utils.down;

import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 李均 on 2016/10/28.
 * 通过拦截网路， 对下载进行监听
 */

public class DownloadWithOKHttp3 {

    private OkHttpClient okHttpClient ;
    private DownloadProgressListener progresslistener;
    private String path;
    // 文件分隔符
    private static final String FILE_SEPARATOR = "/";
    // 外存sdcard存放路径
    private static final String FILE_PATH = Environment.getExternalStorageDirectory() + FILE_SEPARATOR +"autoupdate" + FILE_SEPARATOR;
    // 下载应用存放全路径
    private static final String FILE_NAME = FILE_PATH + "student_for_androidmvp.apk";

    public DownloadWithOKHttp3(String url, DownloadProgressListener listener) {
        this.path = url;
        this.progresslistener = listener;

        initOkHttp();
    }

    /**
     * 使用OKhttp3 get 请求方式下载APK文件 ,
     */
    private void initOkHttp() {
        if(null != okHttpClient)
            return;
        okHttpClient = new OkHttpClient().newBuilder()
                .addNetworkInterceptor(new Interceptor() {  // 添加网路拦截器 ，对下载进度进行监听
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Response response = chain.proceed(chain.request());
                        Logger.d("intercept");
                        return response.newBuilder().body(new ProgressResponseBoby(response.body(),progresslistener)).build();
                    }
                })
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30,TimeUnit.SECONDS)
//                .writeTimeout(30,TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(path)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


                Logger.d("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.d("onResponse");
                if(response.isSuccessful()){

                    //将下载文件以流的形式写入到本地

                    InputStream in = null;
                    FileOutputStream out = null;

                    //创建文件夹
                    File filePath = new File(FILE_PATH);
                    if(!filePath.exists()) {
                        filePath.mkdir();
                    }

                    //创建文件输出流
                    out = new FileOutputStream(new File(FILE_NAME));

                    //从response.boby() 中读取输入流信息
                    in = response.body().byteStream();

                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();

                    if(out != null) {
                        out.close();
                    }
                    if(in != null) {
                        in.close();
                    }
                    if(response !=null){
                        response.close();
                    }
                }
            }
        });
    }




}
