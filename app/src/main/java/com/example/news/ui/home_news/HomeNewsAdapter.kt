package com.example.news.ui.home_news

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.data.model.NewsItem
import com.example.news.databinding.HomeNewsItemsBinding
import kotlinx.android.synthetic.main.home_news_items.view.*

class HomeNewsAdapter(data: List<NewsItem>) : RecyclerView.Adapter<HomeNewsAdapter.ViewHolder>() {

    var articles: List<NewsItem> = data
    lateinit var binding: HomeNewsItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.home_news_items, parent, false)
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


    class ViewHolder(view: HomeNewsItemsBinding) : RecyclerView.ViewHolder(view.root) {
        val bindingItem: HomeNewsItemsBinding = view
    }

}