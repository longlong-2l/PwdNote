package com.surpassli.common.utils

import android.content.ContentResolver
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager

/**
 * @Author surpassli
 * @Description 通用工具类，一般是用作是手机信息，比如屏幕分辨率、IMEI、AndroidId，dp转化等
 * @Version 1.0
 * @Date 2021/6/23 4:18 PM
 */
class CommonUtil {
    companion object {

        fun getAndroidId(contentResolver: ContentResolver?): String {
            return if (contentResolver != null) {
                Settings.System.getString(contentResolver, Settings.Secure.ANDROID_ID)
            } else {
                ""
            }
        }

        fun getAndroidIMEI(context: Context): String {
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val sdkINT = Build.VERSION.SDK_INT
            if (sdkINT >= Build.VERSION_CODES.O && sdkINT < Build.VERSION_CODES.Q) {
                return "tm.imei"
            } else if (sdkINT < Build.VERSION_CODES.O) {
                return "tm.deviceId"
            }
            return ""
        }

        fun dp2px(context: Context, value: Float): Int {
            val density = context.resources.displayMetrics.density
            return (value * density + 0.5f).toInt()
        }

        fun sp2px(context: Context, value: Float): Int {
            val density = context.resources.displayMetrics.scaledDensity
            return (value * density + 0.5f).toInt()
        }

        fun getDisplayHeight(context: Context): Int {
            val dm = context.resources.displayMetrics
            return dm.heightPixels
        }

        fun getDisplayWidth(context: Context): Int {
            val dm = context.resources.displayMetrics
            return dm.widthPixels
        }
    }
}