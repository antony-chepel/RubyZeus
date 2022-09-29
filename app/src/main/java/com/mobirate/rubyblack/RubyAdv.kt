package com.mobirate.rubyblack

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RubyAdv(val context: Context) {

    private val sffwdqfdq = AdvertisingIdClient(context.applicationContext)

    suspend fun yststdtqwd(): String =
        withContext(Dispatchers.IO) {
            sffwdqfdq.start()
            val adIdInfo = sffwdqfdq.info
            Log.d("getAdvertisingId = ",adIdInfo.id.toString())
            sffwdqfdq.finish()
            adIdInfo.id
        }
}