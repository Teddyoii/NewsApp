package com.alhan.arimacnewsapp.ui.base.listeners

import com.alhan.arimacnewsapp.data.dto.news.NewsItem

interface RecyclerItemListener {
    fun onItemSelected(news : com.alhan.arimacnewsapp.data.dto.news.NewsItem)
}