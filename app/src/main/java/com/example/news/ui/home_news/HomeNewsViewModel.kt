package com.example.news.ui.home_news

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.news.data.model.News

class HomeNewsViewModel(app: Application) : AndroidViewModel(app) {

    var context: Context = app
    val allNews: LiveData<News>
        get() = HomeNewsRepo.fetchData(context, "in")


}