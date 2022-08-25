package com.alhan.arimacnewsapp.di

import com.judelakshitha.arimacnewsapp.data.error.mapper.ErrorMapper
import com.judelakshitha.arimacnewsapp.data.error.mapper.ErrorMapperSource
import com.judelakshitha.arimacnewsapp.data.usecase.errors.ErrorManager
import com.judelakshitha.arimacnewsapp.data.usecase.errors.ErrorUseCase

abstract class ErrorModule {

    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource

}