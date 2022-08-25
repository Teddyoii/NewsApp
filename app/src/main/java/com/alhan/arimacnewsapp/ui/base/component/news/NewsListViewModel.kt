package com.alhan.arimacnewsapp.ui.base.component.news

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alhan.arimacnewsapp.data.DataRepositorySource
import com.alhan.arimacnewsapp.data.Resource
import com.alhan.arimacnewsapp.data.dto.news.News
import com.alhan.arimacnewsapp.data.dto.news.NewsItem
import com.judelakshitha.arimacnewsapp.ui.base.BaseViewModel
import java.util.*

class NewsListViewModel
constructor(private val dataRepositoryRepository: com.alhan.arimacnewsapp.data.DataRepositorySource) : BaseViewModel(){


    val newsLiveDataPrivate = MutableLiveData<com.alhan.arimacnewsapp.data.Resource<com.alhan.arimacnewsapp.data.dto.news.News>>()
    val newsLiveData: LiveData<com.alhan.arimacnewsapp.data.Resource<com.alhan.arimacnewsapp.data.dto.news.News>> get() = newsLiveDataPrivate

    val newsSearchFoundPrivate: MutableLiveData<com.alhan.arimacnewsapp.data.dto.news.NewsItem> = MutableLiveData()
    val newsSearchFound: LiveData<com.alhan.arimacnewsapp.data.dto.news.NewsItem> get() = newsSearchFoundPrivate

    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    private val openNewsDetailsPrivate = MutableLiveData<SingleEvent<com.alhan.arimacnewsapp.data.dto.news.NewsItem>>()
    val openNewsDetails: LiveData<SingleEvent<com.alhan.arimacnewsapp.data.dto.news.NewsItem>> get() = openNewsDetailsPrivate

    fun getNews(){

    }

    fun openRecipeDetails(recipe: com.alhan.arimacnewsapp.data.dto.news.NewsItem) {
        openNewsDetailsPrivate.value = SingleEvent(recipe)
    }

    fun showToastMessage(errorCode: Int) {
        //val error = errorManager.getError(errorCode)
       val error= errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }

    fun onSearchClick(newsName: String) {
        newsLiveDataPrivate.value?.data?.newsList?.let {
            if (it.isNotEmpty()) {
                for (recipe in it) {
                    if (recipe.name.toLowerCase(Locale.ROOT).contains(newsName.toLowerCase(Locale.ROOT))) {
                        newsSearchFoundPrivate.value = recipe
                        return
                    }
                }
            }
        }
        return noSearchFoundPrivate.postValue(Unit)
    }
}