package com.surpassli.common.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//自定义图片加载类
class ImageLoader private constructor() {

//    private var mImageQueue: RequestQueues ?= null

    private var mConfig: ImageLoaderConfig? = null

    var mImageCache: ImageCache = MemoryCache()
//    private var mImageQueue: RequestQueue

    //只有核心线程数，没有超时规则，当线程空闲时并不会被回收，最大程度现响应外界需求
    var mExecutorService: ExecutorService ?= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    val mUiHandler: Handler = Handler(Looper.getMainLooper())

    fun init(config: ImageLoaderConfig) {
        mConfig = config
//        mImageCache = mConfig?.bitmapCache
//        checkConfig()
    }

    companion object {
        //        private var sInstance: ImageLoader? = null
//        fun getInstance(): ImageLoader? {
//            if (sInstance == null) {
//                synchronized(ImageLoader::class.java) {
//                    if (sInstance == null) {
//                        sInstance = ImageLoader()
//                    }
//                }
//            }
//            return sInstance
//        }
        //kotlin 单例写法
        val sInstance: ImageLoader by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageLoader()
        }

        interface ImageListener {
            fun onComplete(imageView: ImageView, bitmap: Bitmap, uri: String)
        }
    }

    fun displayImageView(url: String, imageView: ImageView) {
        displayImageView(url, imageView, null, null)
    }

    fun displayImageView(url: String, imageView: ImageView, listener: ImageListener?) {
        displayImageView(url, imageView, null, listener)
    }

    fun displayImageView(url: String, imageView: ImageView, config: DisplayConfig?, listener: ImageListener?) {
        val bitmap = mImageCache.get(url)
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            return
        }
        submitLoadRequest(url, imageView)
    }

    private fun submitLoadRequest(imageUrl: String, imageView: ImageView) {
        imageView.tag = imageUrl
        mExecutorService?.submit(Runnable {
            val loadBitmap = downLoadImageView(imageUrl) ?: return@Runnable
            if (imageView.tag == imageUrl) {
                updateImageView(imageView, loadBitmap)
            }
            mImageCache.put(imageUrl, loadBitmap)
        })
    }

    private fun updateImageView(imageView: ImageView, bitmap: Bitmap) {
        mUiHandler.post {
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun downLoadImageView(imageUrl: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val conn = url.openConnection() as HttpURLConnection
            bitmap = BitmapFactory.decodeStream(conn.inputStream)
            conn.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    fun stop() {

    }
}