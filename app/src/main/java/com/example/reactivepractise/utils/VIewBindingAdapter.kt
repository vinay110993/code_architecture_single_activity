package com.example.reactivepractise.utils

import android.graphics.Color
import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object VIewBindingAdapter{

    @JvmStatic
    @BindingAdapter("image_url")
    fun bindImage(view: ImageView, url: String){
        view.context.setImage(view, url)
    }

    @JvmStatic
    @BindingAdapter("view_text", "view_text_color", requireAll = false)
    fun setText(view: View, textString: String, textColor: Int = Color.BLACK){
        when (view) {
            is TextView -> {
                view.text = textString
                view.setTextColor(textColor)
            }
            else -> { }
        }
    }


}