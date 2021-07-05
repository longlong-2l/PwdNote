package com.surpassli.common.imageloader

import android.graphics.Bitmap
import android.util.LruCache

//图片缓存
class MemoryCache : ImageCache {
    var mImageCache: LruCache<String, Bitmap>? = null

    init {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()   //计算可用的最大内存
        val cacheSize = maxMemory / 4  //提取1/4可用内存作为缓存
        mImageCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return if (value != null) {
                    value.rowBytes * value.height / 1024
                } else {
                    0
                }
            }
        }
    }

    override fun put(url: String, bmp: Bitmap) {
        mImageCache?.put(url, bmp)
    }

    override fun get(url: String): Bitmap? {
        return mImageCache?.get(url)
    }
}