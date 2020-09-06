package com.example.android.githubaccountsearch.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.githubaccountsearch.R

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, src: String?) {
    when {
        src.isNullOrEmpty() -> {
            // when account is fetched drawable is null
            if (view.drawable == null)
                view.setImageResource(R.drawable.loading_animation)
            // when account fetching returns with error, image is set again
            else
                view.setImageResource(R.drawable.error_image)
        }
        else -> {
            Glide.with(view)
                .load(src)
                .into(view)
        }
    }
}