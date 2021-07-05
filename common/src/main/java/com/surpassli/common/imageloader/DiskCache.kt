package com.surpassli.common.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileNotFoundException
import java.io.FileOutputStream

class DiskCache : ImageCache {
    val cacheDir = "sdcard/cache/"

    override fun get(url: String): Bitmap {
        return BitmapFactory.decodeFile(url)
    }

    override fun put(url: String, bmp: Bitmap) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(cacheDir + url)
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } finally {
            CloseUtils.closeQuietly(fileOutputStream)
        }
    }
}