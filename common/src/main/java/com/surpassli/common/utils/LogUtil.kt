package com.surpassli.common.utils

import android.util.Log

/**
 * @Author surpassli
 * @Description 日志打印工具，仅仅打印日志，主要是方便控制，比如在正式线是不应该打印很多日志的
 * @Version 1.0
 * @Date 2021/6/23 4:14 PM
 */
class LogUtil {
    companion object {
        fun i(tag: String, msg: String) {
            Log.i(tag, msg)
        }

        fun d(tag: String, msg: String) {
            Log.d(tag, msg)
        }

        fun v(tag: String, msg: String) {
            Log.v(tag, msg)
        }

        fun w(tag: String, msg: String) {
            Log.v(tag, msg)
        }

        fun e(tag: String, msg: String) {
            Log.e(tag, msg)
        }
    }
}