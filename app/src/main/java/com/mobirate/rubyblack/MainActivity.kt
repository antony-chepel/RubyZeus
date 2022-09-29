package com.mobirate.rubyblack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.viewModels
import com.appsflyer.AppsFlyerLib
import com.mobirate.R
import com.mobirate.rubyblack.RUBYCONST.DEV
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val viewModel: RubyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("ActivityPREF", MODE_PRIVATE)
        if (prefs.getBoolean("activity_exec", false)) {
            Intent(this, RubyFilt::class.java).also { startActivity(it) }
            finish()
        } else {
            val exec = prefs.edit()
            exec.putBoolean("activity_exec", true)
            exec.apply()
        }
        Log.d("DevChecker", sgwqywyeqwe(this).toString())
        Hawk.put(DEV, sgwqywyeqwe(this).toString())

        viewModel.xvfxfsdqwqwqw(this)
        AppsFlyerLib.getInstance()
            .init(RUBYCONST.AF_DEV_KEY, viewModel.conversionDataListener, applicationContext)
        AppsFlyerLib.getInstance().start(this)
        gsgdggqwdq(1500)
    }
    private fun toTestGrounds() {
        Intent(this, RubyFilt::class.java)
            .also { startActivity(it) }
        finish()

    }

    private fun gsgdggqwdq(timeInterval: Long): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            while (NonCancellable.isActive) {
                val hawk1: String? = Hawk.get(RUBYCONST.C1)
                if (hawk1 != null) {
                    Log.d("TestInUIHawk", hawk1.toString())
                    toTestGrounds()
                    break
                } else {
                    val hawk1: String? = Hawk.get(RUBYCONST.C1)
                    Log.d("TestInUIHawkNulled", hawk1.toString())
                    delay(timeInterval)
                }
            }
        }
    }
    private fun sgwqywyeqwe(context: Context): Boolean {
        return run {
            Settings.Secure.getInt(context.contentResolver,
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
        }
    }
}