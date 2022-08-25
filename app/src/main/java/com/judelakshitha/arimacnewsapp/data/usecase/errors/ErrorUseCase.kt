package com.judelakshitha.arimacnewsapp.data.usecase.errors

interface ErrorUseCase {
    fun getError(errorCode: Int): Error

}