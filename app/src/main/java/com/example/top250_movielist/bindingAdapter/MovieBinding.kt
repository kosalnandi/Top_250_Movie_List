package com.example.top250_movielist.bindingAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.top250_movielist.data.network.MovieApi
import com.example.top250_movielist.models.Json
import com.example.top250_movielist.util.NetworkResult

class MovieBinding {


    companion object {

        @BindingAdapter("apiResponse", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<Json>?
        ) {
            if (apiResponse is NetworkResult.Error) {
                imageView.visibility = View.VISIBLE
            }

            if(apiResponse is NetworkResult.Loading) {
                imageView.visibility = View.INVISIBLE
            }
            if (apiResponse is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("apiResponse2", requireAll = true)
        @JvmStatic
        fun errorTextViewVisibility(
            textView: TextView,
            apiResponse: NetworkResult<Json>?
        ) {
            if (apiResponse is NetworkResult.Error) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            }

            if(apiResponse is NetworkResult.Loading) {
                textView.visibility = View.INVISIBLE
            }
            if (apiResponse is NetworkResult.Success) {
                textView.visibility = View.INVISIBLE
            }
        }
    }
}