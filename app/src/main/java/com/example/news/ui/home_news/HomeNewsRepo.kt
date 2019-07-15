package com.example.news.ui.home_news

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.news.data.model.News
import com.example.news.data.network.ApiClient
import com.example.news.data.network.ApiClientInterface
import com.example.news.data.network.FetchNewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewsRepo {




    companion object {
        fun fetchData(context: Context, country_code: String): MutableLiveData<List<News>> {
            var allNews = MutableLiveData<List<News>>()
            val apiClientInterface: ApiClientInterface? = ApiClient.getApiClient()?.create(ApiClientInterface::class.java)
            val news: Call<List<News>>? = apiClientInterface?.getAllNewsByCountry(country_code)


            news?.enqueue(object: Callback<List<News>> {
                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    Log.d("TAGGGG", "Error: "+t.message)
                    Log.d("TAGGGG", "Error: "+t.cause)
                    Log.d("TAGGGG", "Error: "+t.localizedMessage)


                }

                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    Log.d("TAGGGG", "Response: " +response.headers())
                    Log.d("TAGGGG", "Response: " +response.raw())

                    Log.d("TAGGGG", "Response: " +response.message())

                    Log.d("TAGGGG", "Response: " +response.body())

                    allNews.value = response.body()
                }
            })
            return allNews
        }
    }

}