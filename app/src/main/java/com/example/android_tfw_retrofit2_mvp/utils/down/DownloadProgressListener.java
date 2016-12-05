package com.example.android_tfw_retrofit2_mvp.utils.down;

/**
 * Created by 李均 on 2016/10/28.
 */

public interface DownloadProgressListener {

    public void onProgress(int p);

    public void onDown(long t);
}
