package com.surpassli.pwdnote

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class MainApplication : Application() {

    private val mIsBugARouter = true
    override fun onCreate() {
        super.onCreate()
        initARouter()
    }

    private fun initARouter() {
        if (mIsBugARouter) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}