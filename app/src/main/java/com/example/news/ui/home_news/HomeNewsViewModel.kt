package com.example.news.ui.home_news

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.model.News

class HomeNewsViewModel(app: Application) : AndroidViewModel(app) {

    /**
    * @return @Nullable LiveData<News>
    * */
    var allNews: LiveData<News> = MutableLiveData<News>()

    /**
    * Fetch Data From Repository
    * */
    fun getNewsByCountry(country_code: String) {
        allNews = HomeNewsRepo.fetchNewsByCountry(country_code)
    }
}