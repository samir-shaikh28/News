package com.example.news.ui.search_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.data.model.News

class SearchNewsViewModel : ViewModel() {


    /**
     * @return @Nullable LiveData<News>
     * */
    var searchNews: LiveData<News> = MutableLiveData<News>()


    /**
     * Fetch User Search Data From Repository
     * */
    fun getNewsBySearchQuery(searchQuery: String) {
        searchNews= SearchNewsRepo.fetchNewsBySearchQuery(searchQuery)
    }
}