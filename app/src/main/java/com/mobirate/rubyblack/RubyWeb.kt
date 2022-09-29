package com.mobirate.rubyblack

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerLib
import com.mobirate.R
import com.mobirate.rubyblack.RUBYCONST.C1
import com.mobirate.rubyblack.RUBYCONST.D1
import com.mobirate.rubyblack.RUBYCONST.MAIN_ID
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException

class RubyWeb : AppCompatActivity() {
    private val FILECHOOSERRESULTCODE = 1

    // the same for Android 5.0 methods only
    var vxfsfdwqd: ValueCallback<Array<Uri>>? = null
    var psodqwodow: String? = null
    lateinit var vv: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webs)

        vv = findViewById(R.id.viewWeb)

        Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show()

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(vv, true)
        bxvvffdwqdq()
        val activity: Activity = this

        vv.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                try {
                    if (URLUtil.isNetworkUrl(url)) {
                        return false
                    }
                    if (appInstalledOrNot(url)) {
                        Log.d("devx", "ffff")

                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse(url))
                        startActivity(intent)
                    } else {

                        Toast.makeText(
                            applicationContext,
                            "Application is not installed",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")
                            )
                        )
                    }
                    return true
                } catch (e: Exception) {
                    return false
                }
                view.loadUrl(url)
            }


            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                //Save the last visited URL
                xzffcsacascwq(url)
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show()
            }


        }
        vv.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                vxfsfdwqd?.onReceiveValue(null)
                vxfsfdwqd = filePathCallback
                var takePictureIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent!!.resolveActivity(packageManager) != null) {

                    // create the file where the photo should go
                    var photoFile: File? = null
                    try {
                        photoFile = createImageFile()
                        takePictureIntent.putExtra("PhotoPath", psodqwodow)
                    } catch (ex: IOException) {
                        // Error occurred while creating the File
                    }

                    // continue only if the file was successfully created
                    if (photoFile != null) {
                        psodqwodow = "file:" + photoFile.absolutePath
                        takePictureIntent.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile)
                        )
                    } else {
                        takePictureIntent = null
                    }
                }
                val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
                contentSelectionIntent.type = "image/*"
                val intentArray: Array<Intent?> =
                    takePictureIntent?.let { arrayOf(it) } ?: arrayOfNulls(0)
                val chooserIntent = Intent(Intent.ACTION_CHOOSER)
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
                chooserIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.image_chooser))
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
                startActivityForResult(
                    chooserIntent, FILECHOOSERRESULTCODE
                )
                return true
            }

            // creating image files (Lollipop only)
            @Throws(IOException::class)
            private fun createImageFile(): File {
                var imageStorageDir = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "DirectoryNameHere"
                )
                if (!imageStorageDir.exists()) {
                    imageStorageDir.mkdirs()
                }

                // create an image file name
                imageStorageDir =
                    File(imageStorageDir.toString() + File.separator + "IMG_" + System.currentTimeMillis() + ".jpg")
                return imageStorageDir
            }

        }

        vv.loadUrl(uudwyydqdqdw())
    }



    fun strrwqdqwdq(string: String){
// Setting External User Id with Callback Available in SDK Version 4.0.0+
        OneSignal.setExternalUserId(
            string,
            object : OneSignal.OSExternalUserIdUpdateCompletionHandler {
                override fun onSuccess(results: JSONObject) {
                    try {
                        if (results.has("push") && results.getJSONObject("push").has("success")) {
                            val isPushSuccess = results.getJSONObject("push").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for push status: $isPushSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("email") && results.getJSONObject("email").has("success")) {
                            val isEmailSuccess =
                                results.getJSONObject("email").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for email status: $isEmailSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("sms") && results.getJSONObject("sms").has("success")) {
                            val isSmsSuccess = results.getJSONObject("sms").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for sms status: $isSmsSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(error: OneSignal.ExternalIdError) {
                    // The results will contain channel failure statuses
                    // Use this to detect if external_user_id was not set and retry when a better network connection is made
                    OneSignal.onesignalLog(
                        OneSignal.LOG_LEVEL.VERBOSE,
                        "Set external user id done with error: $error"
                    )
                }
            })
    }

    private fun bxvvffdwqdq() {
        val webSettings = vv.settings
        webSettings.javaScriptEnabled = true

        webSettings.useWideViewPort = true

        webSettings.loadWithOverviewMode = true
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.userAgentString = webSettings.userAgentString.replace("; wv", "")

        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.setSupportMultipleWindows(false)

        webSettings.displayZoomControls = false
        webSettings.builtInZoomControls = true
        webSettings.setSupportZoom(true)

        webSettings.pluginState = WebSettings.PluginState.ON
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.setAppCacheEnabled(true)

        webSettings.allowContentAccess = true
    }

    private fun uudwyydqdqdw(): String {

        val spoon = getSharedPreferences("SP_WEBVIEW_PREFS", AppCompatActivity.MODE_PRIVATE)
        val cpOne: String? = Hawk.get(C1)
        val dpOne: String? = Hawk.get(D1)
        val mainid: String = Hawk.get(MAIN_ID)

        val pack = "com.mobirate"

        val afId = AppsFlyerLib.getInstance().getAppsFlyerUID(this)


        AppsFlyerLib.getInstance().setCollectAndroidID(true)
        val one = "sub_id_1="
        val two = "deviceID="
        val three = "ad_id="
        val four = "sub_id_4="
        val five = "sub_id_5="
        val six = "sub_id_6="


        val first = "http://"
        val second = "rubygold.live/go.php?to=2&"

        val namingI = "naming"
        val linkornull = "deeporg"

        val androidVersion = Build.VERSION.RELEASE

        val resultAB = first + second

        var after = ""
        if (cpOne != "null") {
            after =
                "$resultAB$one$cpOne&$two$afId&$three$mainid&$four$pack&$five$androidVersion&$six$namingI"
        } else {
            after =
                "$resultAB$one$dpOne&$two$afId&$three$mainid&$four$pack&$five$androidVersion&$six$linkornull"
        }
        Log.d("TESTAG", "Test Result $after")
        strrwqdqwdq(afId.toString())
        return spoon.getString("SAVED_URL", after).toString()
    }


    private fun appInstalledOrNot(uri: String): Boolean {

        val pm = packageManager
        try {
            Log.d("devx", uri)

            pm.getPackageInfo("org.telegram.messenger", PackageManager.GET_ACTIVITIES)


            return true
        } catch (e: PackageManager.NameNotFoundException) {

        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != FILECHOOSERRESULTCODE || vxfsfdwqd == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        var results: Array<Uri>? = null

        // check that the response is a good one
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (data == null || data.data == null) {
                // if there is not data, then we may have taken a photo
                results = arrayOf(Uri.parse(psodqwodow))
            } else {
                val dataString = data.dataString
                if (dataString != null) {
                    results = arrayOf(Uri.parse(dataString))
                }
            }
        }
        vxfsfdwqd?.onReceiveValue(results)
        vxfsfdwqd = null
    }


    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {


        if (vv.canGoBack()) {
            if (doubleBackToExitPressedOnce) {
                vv.stopLoading()
                vv.loadUrl(firstUrl)
                //Toast.makeText(applicationContext, "attemt loading $firstUrl", Toast.LENGTH_LONG).show()
            }
            this.doubleBackToExitPressedOnce = true
            vv.goBack()
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)

        } else {
            super.onBackPressed()
        }
    }

    var firstUrl = ""
    fun xzffcsacascwq(url: String?) {
        if (!url!!.contains("t.me")) {

            if (firstUrl == "") {
                firstUrl = getSharedPreferences("SP_WEBVIEW_PREFS", AppCompatActivity.MODE_PRIVATE).getString(
                    "SAVED_URL",
                    url
                ).toString()

                val sp = getSharedPreferences("SP_WEBVIEW_PREFS", AppCompatActivity.MODE_PRIVATE)
                val editor = sp.edit()
                editor.putString("SAVED_URL", url)
                editor.apply()
            }
        }
    }

}