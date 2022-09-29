package com.mobirate

import android.app.Application
import android.content.Context
import com.mobirate.rubyblack.RUBYCONST.MAIN_ID
import com.mobirate.rubyblack.RUBYCONST.ONESIGNAL_APP_ID
import com.mobirate.rubyblack.RubyAdv
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppClassRubby : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        GlobalScope.launch(Dispatchers.IO) {
            ystttwqewqe(context = applicationContext)
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

    private suspend fun ystttwqewqe(context: Context) {
            val advertisingInfo = RubyAdv(context)
        val idInfo = advertisingInfo.yststdtqwd()
        Hawk.put(MAIN_ID, idInfo)
    }
}