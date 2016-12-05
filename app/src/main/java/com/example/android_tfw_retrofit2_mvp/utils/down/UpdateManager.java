package com.example.android_tfw_retrofit2_mvp.utils.down;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android_tfw_retrofit2_mvp.dto.UpdateInfo;
import com.example.android_tfw_retrofit2_mvp.utils.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateManager {

	private Context mContext;

	/* 下载包安装路径 */
	private static final String savePath = Environment
			.getExternalStorageDirectory() + "/tfw_androidphone/update/";
	/* 本地APK文件路径 */
	private String apkFile;
	/* 进度条与通知ui刷新的handler和msg常量 */
	private ProgressBar mProgress;
	private int progress;
	private Thread downLoadThread;
	private boolean interceptFlag = false;
	private static  Dialog noticeDialog;
	private Dialog downloadDialog;
	private long downloadId = 0;
	private UpdateInfo updateInfo;
	private SharedPreferences sharedPreferences ;
	private  SharedPreferences.Editor spEditor;
	private  final String DOWNLOADINFO_KEY = "apk_name";

	public UpdateManager(Context context ,UpdateInfo updateInfo) {
		this.mContext = context;
		this.updateInfo = updateInfo;
		sharedPreferences = mContext.getSharedPreferences("DownloadInfo",Context.MODE_PRIVATE);
		spEditor = sharedPreferences.edit();
	}

	/**
	 * 获取版本号
	 *
	 * @param context
	 * @return
	 */
	private int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 获取版本名称
	 *
	 * @param context
	 * @return
	 */
	public static String getVerName(Context context) {
		String verName = null;
		try {
			verName = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("verName:" + verName);
		return verName;
	}

	/**
	 * 发现新版本提示框
	 */
	public void showNoticeDialog() {
		if (sharedPreferences.getString(DOWNLOADINFO_KEY,"").equals(updateInfo.getFile_name())){
			return;
		}
		String updateMsg = "有最新的软件包哦，亲快下载吧~"+updateInfo.getFile_name();
		Builder builder = new Builder(mContext);
		builder.setTitle("软件版本更新");
		builder.setMessage(updateMsg);
		builder.setPositiveButton("立即下载", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				startDownload();
			}
		});
		builder.setNegativeButton("忽略此版本", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO: 2016/11/25  点击忽略此版本则不再提示下载
				spEditor.putString(DOWNLOADINFO_KEY,updateInfo.getFile_name());
				spEditor.commit();
				dialog.dismiss();
			}
		});
		builder.setNeutralButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		noticeDialog = builder.create();
		noticeDialog.show();

		// 设置弹出对话框大小属性
//		WindowManager.LayoutParams lp = noticeDialog.getWindow()
//				.getAttributes();
//		lp.width = 400;
//		lp.height = 500;
//		noticeDialog.getWindow().setAttributes(lp);

	}



	/**
	 * 下载apk
	 */
	public void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					URL url = new URL(updateInfo.getFile_path());

					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					int length = conn.getContentLength();
					InputStream is = conn.getInputStream();

					File file = new File(savePath);
					if (!file.exists()) {
						file.mkdir();
					}
					apkFile = savePath + "ifdoo.apk";
					File ApkFile = new File(apkFile);
					FileOutputStream fos = new FileOutputStream(ApkFile);

					int count = 0;
					byte buf[] = new byte[1024];

					do {
						int numread = is.read(buf);
						count += numread;
						progress = (int) (((float) count / length) * 100);

						// 更新进度
						fos.write(buf, 0, numread);
					} while (!interceptFlag);// 点击取消就停止下载.

					fos.close();
					is.close();
				} else {
					Toast.makeText(mContext, "检测到手机没有存储卡,请安装了内存卡后再升级。",
							Toast.LENGTH_SHORT).show();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 安装apk
	 */
	private void installApk() {
		File apkfile = new File(apkFile);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}

	/**
	 * 检查网络是否可用
	 *
	 * @param context
	 * @return
	 */
	private boolean isNetworkAvailable(Context context) {
		// TODO Auto-generated method stub
		try {

			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netWorkInfo = cm.getActiveNetworkInfo();
			return (netWorkInfo != null && netWorkInfo.isAvailable());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	/**
	 * 开始下载更新包
	 * DownloadManager android 自带下载管理器
	 * DownloadManager是一个为了处理长时间下载Http网络任务的系统下载管理器
	 客户端可以通过URI（下载地址）下载相应的文件。DownloadManager是运行在后台，并负责http交互和重新下载（在失败或者链接改变和系统重启）。
	 DownLoadManager mDownloadManager =(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)；
	 请求下载完成时应当注册一个Broadcast Receiver（action为 ACTION_NOTIFICATION_CLICKED）来适当处理当用户点击运行下载的通知或下载界面；

	 */
	@SuppressWarnings("deprecation")
	private void startDownload() {
		if(!isNetworkAvailable(mContext)){
			ToastUtil.showMessage(mContext,"网路不可用!");
			return;
		}
		// 获取下载服务
		DownloadManager manager =(DownloadManager)mContext.getSystemService(mContext.DOWNLOAD_SERVICE);

//		DownloadManager.Query query = new DownloadManager.Query();
//		query.setFilterById(downloadId);
//		query.setFilterByStatus(DownloadManager.STATUS_RUNNING);//正在下载
//		Cursor c = manager.query(query);
//		if (c.moveToNext()) {
//			//正在下载中，不重新下载
//		} else {

		     LocalBroadcastManager.getInstance(mContext).registerReceiver(new DownloadCompleteReceiver(), IntentFilter.create("",""));

			Log.d("UpdateActivity", "下载地址:"+updateInfo.getFile_path());
			//创建下载请求
			DownloadManager.Request down = new DownloadManager.Request(Uri.parse("http://down.huixueyuan.com/publish_cs/android/TiFenWang.apk"));
		                                    //http://down.huixueyuan.com/publish_cs/wiselearn/wiselearn_pad_V2.1.18.apk
			//设置允许使用的网络类型，这里是移动网络和wifi都可以
			down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
			//显示在下载界面，即下载后的文件在系统下载管理里显示
			down.setVisibleInDownloadsUi(true);
			//设置下载标题
			down.setTitle(updateInfo.getFile_name());
			//显示Notification 下载中和下载完成后都显示
			down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
			//设置下载后文件存放的位置，在SDCard/Android/data/你的应用的包名/files/目录下面
//			down.setDestinationInExternalFilesDir(mContext, null, "tfwHD.apk");
		    down.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "tfwHD.apk");
			//将下载请求放入队列,返回值为downloadId
			downloadId = manager.enqueue(down);
			Log.d("UpdateActivity", "downloadId:" + downloadId);

//		}



	}

}
