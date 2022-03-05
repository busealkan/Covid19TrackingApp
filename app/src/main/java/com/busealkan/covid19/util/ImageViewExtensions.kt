package com.busealkan.covid19

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String?){
    Glide.with(this.context)
            .load(url)
            //.error(R.drawable.interneticon)
            .centerCrop()
            .into(this)
}




