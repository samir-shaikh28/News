package com.example.news.ui.home_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.data.model.News

class HomeNewsViewModel : ViewModel() {

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