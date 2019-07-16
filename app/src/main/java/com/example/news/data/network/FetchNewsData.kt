package com.example.news.data.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable

class FetchNewsData {

    companion object {

        fun fetchData(context: Context, country_code: String): MutableLiveData<News> {
             var allNews = MutableLiveData<News>()
             val apiClientInterface: ApiClientInterface? = ApiClient.getApiClient()?.create(ApiClientInterface::class.java)
             val news: Call<News>? = apiClientInterface?.getAllNewsByCountry(country_code)
             news?.enqueue(object:  Callback<News> {
                 override fun onFailure(call: Call<News>, t: Throwable) {
                     Log.d("TAGGGG", "Error: " +t.message)

                 }

                 override fun onResponse(call: Call<News>, response: Response<News>) {
                     Log.d("TAGGGG", "response" +response.message())
                     Log.d("TAGGGG", "response " +response.raw())


                     allNews.value = response.body()
                 }
             })
             return allNews
        }
    }
}