package com.example.android.githubaccountsearch.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.githubaccountsearch.R

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, src: String?){
    if (src.isNullOrEmpty()){
        view.setImageResource(R.drawable.loading_animation)
    }
    else {
        Glide.with(view)
            .load(src)
            .into(view)
    }
}