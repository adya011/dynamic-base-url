package com.nanda.dynamicbaseurl.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy

object Extensions {
    fun ImageView.loadImage(
        context: Context,
        url: String?,
        placeholder: Int,
        priority: Priority = Priority.HIGH
    ) {
        if ((context as? AppCompatActivity)?.isDestroyed == true) return
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(priority)
            .dontAnimate()
            .placeholder(placeholder)
            .into(this)
    }
}