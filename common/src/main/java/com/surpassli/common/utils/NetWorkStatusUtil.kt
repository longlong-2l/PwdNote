package com.surpassli.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest

/**
 * @Author surpassli
 * @Description 网络工具类，可以检测网络类型，判断并监听网络连接状态，
 * @Version 1.0
 * @Date 2021/6/23 3:49 PM
 */
class NetWorkStatusUtil {
    companion object {

        /**
         * @description 该方法已废弃，不建议使用
         * @param context
         * @return 是否又可用网络连接，不判断具体的连接类型，所以USB、蓝牙、OTG等特殊连接似乎也支持
         */
        @Deprecated(message = "不建议使用")
        fun isNetworkAvailableOld(context: Context?): Boolean {
            val connectivity = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 获取网络连接管理的对象
            val info = connectivity.activeNetworkInfo
            if (info != null && info.isConnected) {
                // 判断当前网络是否已经连接
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
            return false
        }

        /**
         * @return 是否又可用网络连接，目前仅支持WI-FI和移动数据网络，USB、蓝牙、OTG等特殊连接渠道不支持
         * @param context
         */
        fun queryNetAvailable(context: Context?): Boolean {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //这个的判空不可删除，因为有时候会报错，也不知道为啥
            cm?.let {
                val netWork = it.activeNetwork
                netWork?.let {
                    val nc = cm.getNetworkCapabilities(netWork)
                    nc?.let {
                        return nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    }
                }
            }
            return false
        }

        /**
         * @param context
         * @param callbacks 变化回调，一般是断开和连接都有回调
         * @description 最适合于Android 10及以上, 10及以下版本一般使用监听广播的方式
         */
        fun addNetworkChangeListener(context: Context?, callbacks: ConnectivityManager.NetworkCallback) {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val builder = NetworkRequest.Builder()
            builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            cm.registerNetworkCallback(builder.build(), callbacks)
        }

        /**
         * @description 移除网络监听，必须移除，通常在退出应用以后
         */
        fun removeNetworkChangeListener(context: Context?, callbacks: ConnectivityManager.NetworkCallback) {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.unregisterNetworkCallback(callbacks)
        }
    }
}