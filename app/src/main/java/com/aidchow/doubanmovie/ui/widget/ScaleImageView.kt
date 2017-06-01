package com.aidchow.doubanmovie.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView


/**
 * Author: aidchow
 */
@SuppressLint("AppCompatCustomView")
class ScaleImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ImageView(context, attrs) {
    private var initWidth: Int = 0
    private var initHeight: Int = 0

    fun setInitSize(initWidth: Int, initHeight: Int) {
        this.initWidth = initWidth
        this.initHeight = initHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (initWidth > 0 && initHeight > 0) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            var height = MeasureSpec.getSize(heightMeasureSpec)

            val scale = initHeight.toFloat() / initWidth.toFloat()
            if (width > 0) {
                height = (width.toFloat() * scale).toInt()
            }
            setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}