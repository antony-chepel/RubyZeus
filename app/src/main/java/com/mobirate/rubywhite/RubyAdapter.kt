package com.mobirate.rubywhite


import android.view.View

import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import com.mobirate.databinding.RubyItemBinding


import java.lang.Exception
import java.util.*

class RubyAdapter(val ysdwqdqwd :ArrayList<RubyData>) : RecyclerView.Adapter<RubyAdapter.ProfItemHolder>(),TouchRuby.IteamTouchAdapter {
    class ProfItemHolder(val binding : RubyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun ysdyqwydq(odwoqdoqwd : RubyData) = with(binding){
            Picasso.get().load(odwoqdoqwd.img)
                .into(ywydyqwd,object : Callback {
                    override fun onSuccess() {
                        oodwiqdiqw.visibility = View.INVISIBLE
                    }

                    override fun onError(e: Exception?) {

                    }

                } )


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfItemHolder {
        val binding = RubyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProfItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfItemHolder, position: Int) {
        holder.ysdyqwydq(ysdwqdqwd[position])

    }

    override fun getItemCount() = ysdwqdqwd.size
    override fun onMove(firstPos: Int, targetPos: Int) {
        val targetItem = ysdwqdqwd[targetPos]
        ysdwqdqwd[targetPos] = ysdwqdqwd[firstPos]
        ysdwqdqwd[firstPos] = targetItem
        notifyItemMoved(firstPos,targetPos)

    }

    override fun onClear() {

    }


}