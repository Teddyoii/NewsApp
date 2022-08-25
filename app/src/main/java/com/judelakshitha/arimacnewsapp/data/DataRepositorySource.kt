package com.judelakshitha.arimacnewsapp.data

import com.judelakshitha.arimacnewsapp.data.dto.news.News
import com.judelakshitha.arimacnewsapp.data.login.LoginRequest
import com.judelakshitha.arimacnewsapp.data.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun requestNews(): Flow<Resource<News>>
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
   
}