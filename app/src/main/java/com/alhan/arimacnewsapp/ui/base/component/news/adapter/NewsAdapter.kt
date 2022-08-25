package com.alhan.arimacnewsapp.ui.base.component.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alhan.arimacnewsapp.data.dto.news.NewsItem
import com.judelakshitha.arimacnewsapp.databinding.NewsItemBinding
import com.judelakshitha.arimacnewsapp.ui.base.component.news.NewsListViewModel
import com.judelakshitha.arimacnewsapp.ui.base.listeners.RecyclerItemListener

class NewsAdapter(private val newsListViewModel: NewsListViewModel, private val news: List<com.alhan.arimacnewsapp.data.dto.news.NewsItem>): RecyclerView.Adapter<NewsViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(news: com.alhan.arimacnewsapp.data.dto.news.NewsItem) {
            //newsListViewModel.openNewsDetails(news)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return news.size
    }
}