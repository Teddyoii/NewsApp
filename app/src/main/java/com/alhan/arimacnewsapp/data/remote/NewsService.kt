package com.alhan.arimacnewsapp.data.remote

import com.alhan.arimacnewsapp.data.dto.news.NewsItem
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<com.alhan.arimacnewsapp.data.dto.news.NewsItem>>

}