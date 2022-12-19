package com.example.top250_movielist

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.top250_movielist.data.Repository
import com.example.top250_movielist.models.Json
import com.example.top250_movielist.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    application: MyApplication
): AndroidViewModel(application) {

    val movieResponse: MutableLiveData<NetworkResult<Json>> = MutableLiveData()

    fun getMovie() = viewModelScope.launch {
        getMovieSafeCall()
    }

    private suspend fun getMovieSafeCall() {
        movieResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getMovie()
                movieResponse.value = handleMovieResponse(response)

            }catch (e: Exception) {
                movieResponse.value = NetworkResult.Error("Movie Not Found")
            }
        }
        else {
            movieResponse.value = NetworkResult.Error("NO Internet Connection")
        }
    }

    private fun handleMovieResponse(response: Response<Json>): NetworkResult<Json>? {

        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.items.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val movieList = response.body()
                return NetworkResult.Success(movieList!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

            else -> false
        }
    }
}