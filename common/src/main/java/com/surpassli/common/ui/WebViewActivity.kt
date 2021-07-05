package com.surpassli.common.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.surpassli.common.R

/**
 * @Author surpassli
 * @Description H5展示页，用于展示所有H5相关的内容
 * @Version 1.0
 * @Date 2021/6/28 2:40 PM
 */
class WebViewActivity : AppCompatActivity() {

    private lateinit var webViewViewModel: WebViewViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
    }
}