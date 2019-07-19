package com.example.news.ui.home_news

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.news.data.db.NewsDatabaseInstance
import com.example.news.data.model.News
import com.example.news.data.network.ApiClient
import com.example.news.data.network.ApiClientInterface
import com.example.news.data.network.FetchNewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewsRepo {

    companion object {
        // private var allNew: News? = null
        private var allNews: LiveData<News> = MutableLiveData<News>()
        private val fetchNewsData = FetchNewsData()

        /**
         * Fetch Data from server
         * @param country_code String
         * @return @Nullable MutableLiveData<News>
         */
        fun fetchNewsByCountry(country_code: String, context: Context): LiveData<News> {
            allNews = NewsDatabaseInstance.getDbInstance(context).dao().selectAllNews()
            allNews.observeForever {
                if (it == null) {
                    fetchNewsData.fetchNewsByCountry(country_code, context)
                }
            }
            return allNews

        }
    }
}