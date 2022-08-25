package com.alhan.arimacnewsapp.di

import com.alhan.arimacnewsapp.data.DataRepository
import com.alhan.arimacnewsapp.data.DataRepositorySource

abstract class DataModule {
    abstract fun provideDataRepository(dataRepository: com.alhan.arimacnewsapp.data.DataRepository): com.alhan.arimacnewsapp.data.DataRepositorySource

}