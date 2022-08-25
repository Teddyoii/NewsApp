package com.alhan.arimacnewsapp.data.remote

import com.alhan.arimacnewsapp.data.Resource
import com.alhan.arimacnewsapp.data.dto.news.News

interface RemoteDataSource {
    suspend fun requestNews(): com.alhan.arimacnewsapp.data.Resource<com.alhan.arimacnewsapp.data.dto.news.News>
}