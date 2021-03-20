package com.example.pokemongo.ui.binding

import android.annotation.SuppressLint
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pokemongo.R
import com.example.pokemongo.common.common.Utils

@BindingAdapter("bindingSrcUrl")
fun bindingSrcUrl(iv: AppCompatImageView, url: String) {
    val srcUrl = Utils.getSrcUrl(url)
    Glide.with(iv.context).load(srcUrl).placeholder(R.drawable.ic_baseline_image_24).into(iv)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("bindingIndex")
fun bindingIndex(tvIndex: AppCompatTextView, url: String) {
    val index = Utils.getIndex(url)
    tvIndex.text = "#$index"
}

@BindingAdapter("bindingUrl")
fun bindingUrl(iv: AppCompatImageView, url: String) {
    Glide.with(iv.context).load(url).placeholder(R.drawable.ic_baseline_image_24).into(iv)
}