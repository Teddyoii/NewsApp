package com.judelakshitha.arimacnewsapp.ui.base.component.news

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.judelakshitha.arimacnewsapp.data.DataRepositorySource
import com.judelakshitha.arimacnewsapp.data.Resource
import com.judelakshitha.arimacnewsapp.data.dto.news.News
import com.judelakshitha.arimacnewsapp.data.dto.news.NewsItem
import com.judelakshitha.arimacnewsapp.ui.base.BaseViewModel
import java.util.*

class NewsListViewModel
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel(){


    val newsLiveDataPrivate = MutableLiveData<Resource<News>>()
    val newsLiveData: LiveData<Resource<News>> get() = newsLiveDataPrivate

    val newsSearchFoundPrivate: MutableLiveData<NewsItem> = MutableLiveData()
    val newsSearchFound: LiveData<NewsItem> get() = newsSearchFoundPrivate

    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    private val openNewsDetailsPrivate = MutableLiveData<SingleEvent<NewsItem>>()
    val openNewsDetails: LiveData<SingleEvent<NewsItem>> get() = openNewsDetailsPrivate

    fun getNews(){

    }

    fun openRecipeDetails(recipe: NewsItem) {
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