package com.alhan.arimacnewsapp.data

import com.alhan.arimacnewsapp.data.dto.news.News
import com.judelakshitha.arimacnewsapp.data.login.LoginRequest
import com.judelakshitha.arimacnewsapp.data.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun requestNews(): Flow<com.alhan.arimacnewsapp.data.Resource<com.alhan.arimacnewsapp.data.dto.news.News>>
    suspend fun doLogin(loginRequest: LoginRequest): Flow<com.alhan.arimacnewsapp.data.Resource<LoginResponse>>
   
}