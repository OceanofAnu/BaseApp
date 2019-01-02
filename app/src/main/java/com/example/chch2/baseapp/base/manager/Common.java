package com.example.chch2.baseapp.base.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class Common {

	/**
	 * 判断SD卡
	 * 
	 * @return
	 */
	@SuppressLint("SdCardPath")
	public static String hasSdCard() {
		String file_path = "";
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			file_path = Environment.getExternalStorageDirectory() + "/mpos/tmp";
		} else {
			file_path = "/data/data/com.chinaums.mpos/files";
		}
		return file_path;
	}

	/**
	 * 删除apk
	 * 
	 * @param file
	 */
	public static void deleteApkFile(File file) {
		if (file.exists() && file.isDirectory()) {
			file.delete();
		}
	}

	/**
	 * 删除apk
	 * 
	 * @param fileName
	 * @param path
	 */
	public static void deleteApkFile(String fileName, String path) {
		File file = new File(path);
		if (file.exists()) {
			File mFileDesc = new File(file.getAbsolutePath(), fileName);
			if (mFileDesc.exists() && mFileDesc.isFile()) {
				file.delete();
			}
		}
	}

	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 安装新应用
	 */
	public static void installApk(Context context, Uri apk) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(apk,"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
