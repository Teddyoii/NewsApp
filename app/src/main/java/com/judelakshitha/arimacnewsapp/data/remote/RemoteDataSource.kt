package com.judelakshitha.arimacnewsapp.data.remote

import com.judelakshitha.arimacnewsapp.data.Resource
import com.judelakshitha.arimacnewsapp.data.dto.news.News

interface RemoteDataSource {
    suspend fun requestNews(): Resource<News>
}