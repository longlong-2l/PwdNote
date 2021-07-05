package com.surpassli.common.imageloader

import android.graphics.Bitmap

interface ImageCache {
    fun put(url: String, bmp: Bitmap)
    fun get(url: String): Bitmap ?
}