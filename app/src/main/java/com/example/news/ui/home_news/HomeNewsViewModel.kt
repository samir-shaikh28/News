package com.example.news.ui.home_news

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.model.News

class HomeNewsViewModel(app: Application) : AndroidViewModel(app) {

//    var context: Context = app
    var allNews: LiveData<News> = MutableLiveData<News>()

//    init{
//        allNews = HomeNewsRepo.fetchNewsByCountry(context, "in")
//    }

    fun getNewsByCountry(context: Context, country_code: String) {
        Log.d("TAGGGG", "In View Model")
        allNews = HomeNewsRepo.fetchNewsByCountry(context, country_code)
    }



}