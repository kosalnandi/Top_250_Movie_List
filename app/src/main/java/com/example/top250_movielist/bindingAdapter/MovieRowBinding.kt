package com.example.top250_movielist.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

class MovieRowBinding {

    companion object {

        @BindingAdapter("loadImageUrls")
        @JvmStatic
        fun loadImageUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }
    }
}