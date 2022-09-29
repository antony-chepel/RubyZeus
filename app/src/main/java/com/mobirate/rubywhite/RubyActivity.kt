package com.mobirate.rubywhite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.mobirate.R

class RubyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_ruby)
        val ystdqtwd = findViewById<Button>(R.id.button2)
        ystdqtwd.setOnClickListener {
            startActivity(Intent(this,MainRubyScreen::class.java))
        }
    }
}