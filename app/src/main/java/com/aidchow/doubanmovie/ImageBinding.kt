package com.aidchow.doubanmovie

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by aidchow on 17-5-29.
 */
object ImageBinding {
    @BindingAdapter("android:src")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {

        Glide.with(view.context)
                .load(url)
                .placeholder(R.drawable.ic_image_black)
                .into(view)
    }
}