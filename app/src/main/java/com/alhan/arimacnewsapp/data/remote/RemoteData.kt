package com.alhan.arimacnewsapp.data.remote

import com.alhan.arimacnewsapp.data.Resource
import com.alhan.arimacnewsapp.data.dto.news.News
import com.alhan.arimacnewsapp.data.dto.news.NewsItem
import com.judelakshitha.arimacnewsapp.data.error.NETWORK_ERROR
import com.judelakshitha.arimacnewsapp.data.error.NO_INTERNET_CONNECTION
import com.judelakshitha.arimacnewsapp.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException

class RemoteData constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity):RemoteDataSource{
    override suspend fun requestNews(): com.alhan.arimacnewsapp.data.Resource<com.alhan.arimacnewsapp.data.dto.news.News> {
        val recipesService = serviceGenerator.createService(NewsService::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is List<*> -> {
                com.alhan.arimacnewsapp.data.Resource.Success(data = com.alhan.arimacnewsapp.data.dto.news.News(
                    response as ArrayList<com.alhan.arimacnewsapp.data.dto.news.NewsItem>
                )
                )
            }
            else -> {
                com.alhan.arimacnewsapp.data.Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }



}