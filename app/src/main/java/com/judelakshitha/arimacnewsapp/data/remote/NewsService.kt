package com.judelakshitha.arimacnewsapp.data.remote

import com.judelakshitha.arimacnewsapp.data.dto.news.NewsItem
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<NewsItem>>

}