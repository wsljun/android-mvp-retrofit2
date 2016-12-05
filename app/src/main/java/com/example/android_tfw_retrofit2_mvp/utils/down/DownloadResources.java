package com.example.android_tfw_retrofit2_mvp.utils.down;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import com.example.android_tfw_retrofit2_mvp.R;
import com.example.android_tfw_retrofit2_mvp.utils.Constants;
import com.example.android_tfw_retrofit2_mvp.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by 李均 on 2016/9/21.
 * 下载资源类，文档
 */

public class DownloadResources {
    private Context context;
    private String fileName; //文件名
    private String downUrl;  //下载地址
    private String filePath; //文件保存地址
    private String folderPath = Constants.ENVIROMENT_DIR_CACHE+ "renwuta"; // 文件夹

    public DownloadResources(Context context, String path, String url){
        this.context = context;
        this.fileName = path;
        this.downUrl = url;
    }

    /**
     * 初始化文件信息
     */
    public void initFile(){
        // 拼接文件完整路径
        filePath = folderPath+"/"+fileName;// /storage/emulated/0/student_for_androidPhone/renwuta/fileName
        filePath = filePath+ FileUtils.getFileType(downUrl);

        // 先去查询是否已经存在此文件,如果存在此文件则返回文件完整地址
        if(FileUtils.isFilesExists(filePath)){
            openFile(filePath);
//            ToastUtil.showMessage(context,"文件已存在");
        }else{
            //没有文件，则先创建文件
            createDir();
            // 创建保存地址之后，开始下载资源
            downFile(downUrl);
        }
    }

    /**
     * @param url 下载资源 xUtils
     */
    private void downFile(String url){
      /** xUtils

       HttpUtils http = new HttpUtils();
        http.download(url, filePath, true, true, new RequestCallBack<File>() {
            @Override
            public void onStart() {
                ToastUtil.showMessage(context,"请稍等，正在后台下载...");
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {

            }

            @Override
            public void onFailure(HttpException error, String msg) {
                ToastUtil.showMessage(context,"下载失败，请重新尝试");
            }

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                ToastUtil.showMessage(context,"下载成功，即将调用第三方应用打开");
                openFile(filePath);
            }
        });*/
    }

    /**
     * 使用 intent 选择第三方应用 打开文件
     * @param filepath
     */
    public void openFile(String filepath)
    {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(new File(filepath));
            //获取文件file的MIME类型
            String type = FileUtils.getMIMEType(new File(filepath));
            //设置intent的data和Type属性。
            intent.setDataAndType(uri, type);
            context.startActivity(intent);
        }catch (Exception e){
//            ToastUtil.showMessage(context, context.getResources().getString(R.string.unable_to_open), 5 * 1000);
        }
    }


    private void createDir() {
        if (! FileUtils.isSdCardMounted())
            return;
        FileUtils.CreateDir(folderPath); // 创建文件夹
        try {
            File file=new File(filePath);
            file.createNewFile(); // 创建文件

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;
    }
}
