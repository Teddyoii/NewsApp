package com.alhan.arimacnewsapp.data

import com.alhan.arimacnewsapp.data.dto.news.News
import com.judelakshitha.arimacnewsapp.data.local.LocalData
import com.judelakshitha.arimacnewsapp.data.login.LoginRequest
import com.judelakshitha.arimacnewsapp.data.login.LoginResponse
import com.judelakshitha.arimacnewsapp.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class DataRepository constructor(private val remoteRepository: RemoteData, private val localRepository: LocalData, private val ioDispatcher: CoroutineContext):
    com.alhan.arimacnewsapp.data.DataRepositorySource {
    override suspend fun requestNews(): Flow<com.alhan.arimacnewsapp.data.Resource<com.alhan.arimacnewsapp.data.dto.news.News>> {
      return flow {
          emit(remoteRepository.requestNews())
      }.flowOn(ioDispatcher)
    }

    override suspend fun doLogin(loginRequest: LoginRequest): Flow<com.alhan.arimacnewsapp.data.Resource<LoginResponse>> {
        return flow {

        }
    }

}