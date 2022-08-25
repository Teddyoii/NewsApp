package com.judelakshitha.arimacnewsapp.di

import com.judelakshitha.arimacnewsapp.data.DataRepository
import com.judelakshitha.arimacnewsapp.data.DataRepositorySource

abstract class DataModule {
    abstract fun provideDataRepository(dataRepository: DataRepository): DataRepositorySource

}