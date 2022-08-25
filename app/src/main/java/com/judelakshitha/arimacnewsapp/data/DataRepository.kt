package com.judelakshitha.arimacnewsapp.data

import com.judelakshitha.arimacnewsapp.data.dto.news.News
import com.judelakshitha.arimacnewsapp.data.local.LocalData
import com.judelakshitha.arimacnewsapp.data.login.LoginRequest
import com.judelakshitha.arimacnewsapp.data.login.LoginResponse
import com.judelakshitha.arimacnewsapp.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class DataRepository constructor(private val remoteRepository: RemoteData, private val localRepository: LocalData, private val ioDispatcher: CoroutineContext):DataRepositorySource{
    override suspend fun requestNews(): Flow<Resource<News>> {
      return flow {
          emit(remoteRepository.requestNews())
      }.flowOn(ioDispatcher)
    }

    override suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {

        }
    }

}