package com.example.top250_movielist.models


import com.google.gson.annotations.SerializedName

data class Json(
    @SerializedName("errorMessage")
    val errorMessage: String,
    @SerializedName("items")
    val items: List<Item>
)