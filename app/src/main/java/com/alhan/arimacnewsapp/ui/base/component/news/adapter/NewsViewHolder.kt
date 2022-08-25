package com.alhan.arimacnewsapp.ui.base.component.news.adapter

import androidx.recyclerview.widget.RecyclerView
import com.judelakshitha.arimacnewsapp.R
import com.alhan.arimacnewsapp.data.dto.news.NewsItem
import com.judelakshitha.arimacnewsapp.databinding.NewsItemBinding
import com.judelakshitha.arimacnewsapp.ui.base.listeners.RecyclerItemListener
import com.squareup.picasso.Picasso
import org.mockito.internal.verification.VerificationModeFactory.description

class NewsViewHolder(private val itemBinding: NewsItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(newsItem: com.alhan.arimacnewsapp.data.dto.news.NewsItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvCaption.text =newsItem.description
        itemBinding.tvName.text = newsItem.name
        //Picasso.get().load().placeholder().error().into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(com.alhan.arimacnewsapp.data.dto.news.NewsItem()) }
    }
}