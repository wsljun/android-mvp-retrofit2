package com.example.android_tfw_retrofit2_mvp.utils.down;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by 李均 on 2016/10/28.
 * 重写 Responsebody 用来监听加载进度
 * 处理请求时 原生的ResponseBoby
 *
 */

public class ProgressResponseBoby extends ResponseBody {

    private ResponseBody mResponseBoby;
    private BufferedSource mBufferedSource;
    private DownloadProgressListener listener;

    public ProgressResponseBoby(ResponseBody mResponseBoby
            , DownloadProgressListener listener) {
        this.mResponseBoby = mResponseBoby;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return mResponseBoby.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBoby.contentLength();
    }

    @Override
    public BufferedSource source() {
        if(mBufferedSource ==  null){
            mBufferedSource = Okio.buffer(getSource(mResponseBoby.source()));
        }
        return mBufferedSource;
    }

    private Source getSource(Source source){

        return new ForwardingSource(source) {

            long totalSize = 0;
            long sum = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {

                if(totalSize==0){
                    totalSize = contentLength();
                }
                Logger.d(totalSize);

                long len = super.read(sink, byteCount);

                sum +=(len==-1?0:len);

                int progress = (int) ((sum*1.0f/totalSize)*100);
                Logger.d(progress);

                if(len ==-1){
                    listener.onDown(totalSize);
                }else{
                    listener.onProgress(progress);
                }



                return len;
            }
        };
    }
}
