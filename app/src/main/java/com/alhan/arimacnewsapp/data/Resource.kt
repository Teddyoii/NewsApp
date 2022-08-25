package com.alhan.arimacnewsapp.data

sealed class Resource<T>(
    val data: T? = null,
    val errorCode: Int? = null
){
    class Success<T>(data: T) : com.alhan.arimacnewsapp.data.Resource<T>(data)
    class Loading<T>(data: T? = null) : com.alhan.arimacnewsapp.data.Resource<T>(data)
    class DataError<T>(errorCode: Int) : com.alhan.arimacnewsapp.data.Resource<T>(null, errorCode)

    override fun toString(): String {
        return when (this) {
            is com.alhan.arimacnewsapp.data.Resource.Success<*> -> "Success[data=$data]"
            is com.alhan.arimacnewsapp.data.Resource.DataError -> "Error[exception=$errorCode]"
            is com.alhan.arimacnewsapp.data.Resource.Loading<T> -> "Loading"
        }
    }
}
