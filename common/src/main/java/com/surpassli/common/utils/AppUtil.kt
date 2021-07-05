package com.surpassli.common.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

/**
 * @Author surpassli
 * @Description app本身的一些处理工具，比如获取版本号、获取文件路径等
 * @Version 1.0
 * @Date 2021/6/23 4:17 PM
 */
class AppUtil {
    companion object {

        fun getAppVersionCode(context: Context): Long {
            val pm = context.packageManager
            val pmi = pm.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                pmi?.longVersionCode ?: 1L
            } else {
                pmi?.versionCode?.toLong() ?: 1L
            }
        }

        fun getAppVersionName(context: Context): String {
            val pm = context.packageManager
            val pmi = pm.getPackageInfo(context.packageName, PackageManager.GET_CONFIGURATIONS)
            return pmi?.versionName ?: "1.0.0"
        }

        /**
         * @description 检测 APK 是否合法
         * @param apkPath apk路径
         */
        fun checkApkFile(context: Context, apkPath: String): Boolean {
            return context.packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES) != null
        }
    }
}