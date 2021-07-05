package com.surpassli.common.imageloader

import android.graphics.Bitmap

class DoubleCache : ImageCache {
    val mMemoryCache = MemoryCache()
    val mDiskCache = DiskCache()

    override fun get(url: String): Bitmap? {
        var bitmap = mMemoryCache.get(url)
        if (bitmap != null) {
            bitmap = mDiskCache.get(url)
        }
        return bitmap
    }

    override fun put(url: String, bmp: Bitmap) {
        mMemoryCache.put(url, bmp)
        mDiskCache.put(url, bmp)
    }
}