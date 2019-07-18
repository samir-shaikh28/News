package com.example.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.data.model.NewsItem
import com.example.news.databinding.NewsItemsBinding

class NewsItemAdapter(data: List<NewsItem>) : RecyclerView.Adapter<NewsItemAdapter.ViewHolder>() {

    var articles: List<NewsItem> = data
    lateinit var binding: NewsItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.news_items, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.newsItem = articles[position]
    }


    class ViewHolder(view: NewsItemsBinding) : RecyclerView.ViewHolder(view.root) {
        val bindingItem: NewsItemsBinding = view
    }

}