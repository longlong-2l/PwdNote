package com.surpassli.pwdnote
import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

@Interceptor(priority = 1, name = "测试拦截器1")
class InterceptorOne : IInterceptor {
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.d("ARouter", "测试拦截器1 process")
        callback?.onContinue(postcard)
    }

    override fun init(context: Context?) {
        Log.d("ARouter", "测试拦截器1 init")
    }
}