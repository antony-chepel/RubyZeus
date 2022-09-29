package com.mobirate.rubywhite

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.mobirate.R
import com.mobirate.databinding.ActivityMainRubyScreenBinding

class MainRubyScreen : AppCompatActivity() {
    private var osodiqwdiqw: CountDownTimer? = null
    private lateinit var binding: ActivityMainRubyScreenBinding
    private var ysydqwdqw : RubyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRubyScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        odoqwdqwdqd()
        binding.bReady.setOnClickListener{
            startActivity(Intent(this,RubyActivity::class.java))
        }
        sgdgwqdq()
    }

    private fun sgdgwqdq() = with(binding){
        ysydqwdqw = RubyAdapter(RubyImgUtils.listruby)
        rcQueen.layoutManager = GridLayoutManager(this@MainRubyScreen,3)
        rcQueen.adapter = ysydqwdqw
        val dragCallback = TouchRuby(ysydqwdqw!!)
        val touchHelper = ItemTouchHelper(dragCallback)
        touchHelper.attachToRecyclerView(rcQueen)


    }

    private fun odoqwdqwdqd() =with(binding){
        var counter = 15
        osodiqwdiqw?.cancel()
        osodiqwdiqw = object : CountDownTimer(15000,1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                counter--
                if(counter >= 0) {
                    val count = counter.toString()
                    tvCounter.text = "Time : $count sec"

                    when(counter){
                        3-> tvCounter.setTextColor(resources.getColor(R.color.error_color))
                        2-> tvCounter.setTextColor(resources.getColor(R.color.error_color))
                        1-> tvCounter.setTextColor(resources.getColor(R.color.error_color))
                        0-> tvCounter.setTextColor(resources.getColor(R.color.error_color))
                    }
                }
            }

            override fun onFinish() {
                tvCounter.setTextColor(resources.getColor(R.color.white))
                if(counter==0){
                    counter = 10
                    startActivity(Intent(this@MainRubyScreen,RubyActivity::class.java)
                        .putExtra("bad","bad")
                    )
                }


            }

        }.start()
    }


}