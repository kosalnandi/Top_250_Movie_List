package com.example.top250_movielist

import com.example.top250_movielist.models.Json
import retrofit2.http.GET

interface MovieApi {

    @GET("/en/API/Top250Movies/k_hc2sam6f")

   suspend fun getMovie(): Json
}