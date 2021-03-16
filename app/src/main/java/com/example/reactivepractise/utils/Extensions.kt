package com.example.reactivepractise.utils

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide


fun EditText.onChange(): String{
    var result: String =""
    this.addTextChangedListener(object: TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            result = s?.toString() ?: ""
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    return result
}

fun<T : ViewDataBinding?> ViewGroup.inflateLayout(layout: Int): T{
    return DataBindingUtil.inflate<T>(LayoutInflater.from(this.context), layout, this, false)
}

fun String?.getIntValue(defaultValue: Int? = 0) = this?.toIntOrNull() ?: defaultValue ?: 0

fun View.clickCallBack(waitTime: Long= 1*1000, event: ()-> Unit){
    var mLastClickTime = 0L
    if (SystemClock.elapsedRealtime() - mLastClickTime < waitTime){
        return;
    }
    mLastClickTime = SystemClock.elapsedRealtime()
    event
}

fun Context.setImage(view: ImageView, url: String){
    Glide.with(this)
        .load(url)
        .placeholder(com.example.reactivepractise.R.drawable.ic_launcher_background)
        .into(view)
}

fun Context?.showLongToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.hideKeyboard() {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}