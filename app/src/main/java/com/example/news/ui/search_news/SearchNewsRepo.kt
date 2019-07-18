package com.example.news.ui.search_news

import androidx.lifecycle.MutableLiveData
import com.example.news.data.model.News
import com.example.news.data.network.ApiClient
import com.example.news.data.network.ApiClientInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchNewsRepo {

    companion object {
        /**
         * Fetch User Search Data from server
         * @param searchQuery String
         * @return @Nullable MutableLiveData<News>
         */
        fun fetchNewsBySearchQuery(searchQuery: String): MutableLiveData<News> {

            val searchNews = MutableLiveData<News>()
            val apiClientInterface: ApiClientInterface = ApiClient.retrofit.create(ApiClientInterface::class.java)
            val news: Call<News> = apiClientInterface.getSearchedNews(searchQuery)

            news.enqueue(object : Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {}

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    searchNews.value = response.body()
                }
            })
            return searchNews
        }
    }
}