package com.judelakshitha.arimacnewsapp.ui.base.listeners

import com.judelakshitha.arimacnewsapp.data.dto.news.NewsItem

interface RecyclerItemListener {
    fun onItemSelected(news : NewsItem)
}