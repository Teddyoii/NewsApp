package com.judelakshitha.arimacnewsapp.data.usecase.errors

import com.judelakshitha.arimacnewsapp.data.error.mapper.ErrorMapper
import com.judelakshitha.arimacnewsapp.data.error.Error


class ErrorManager constructor(private val errorMapper: ErrorMapper):ErrorUseCase {

    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}