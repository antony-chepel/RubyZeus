package com.mobirate.rubyblack

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.appsflyer.AppsFlyerConversionListener
import com.facebook.applinks.AppLinkData
import com.mobirate.rubyblack.RUBYCONST.D1
import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class RubyViewModel@Inject constructor() : ViewModel() {
    val conversionDataListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {

            val dataGotten = data?.get("campaign").toString()
//                val dataGotten = "apps_sub2_sub3_sub4"
            Hawk.put(RUBYCONST.C1, dataGotten)
            Log.d("devTEST", data.toString())
        }

        override fun onConversionDataFail(p0: String?) {

        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {

        }

        override fun onAttributionFailure(p0: String?) {
        }
    }


    fun xvfxfsdqwqwqw(context: Context) {
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {
                val params = appLinkData.targetUri.host

                Log.d("D11PL", "$params")
//                val conjoined = TextUtils.join("/", params)
//                Log.d("FB_TEST:", conjoined)

                Hawk.put(D1, params.toString())


            }
            if (appLinkData == null) {
                Log.d("FB_ERR:", "Params = null")
            }
        }
    }
}