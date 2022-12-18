package com.example.top250_movielist.data

import com.example.top250_movielist.data.network.MovieApi
import com.example.top250_movielist.models.Json
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun getMovie(): Response<Json> {
        return movieApi.getMovie()
    }
}