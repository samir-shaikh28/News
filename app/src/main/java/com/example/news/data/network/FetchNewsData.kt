package com.example.news.data.network

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.db.NewsDao
import com.example.news.data.db.NewsDatabaseInstance
import com.example.news.data.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable

class FetchNewsData {



   fun fetchNewsByCountry(country_code: String, context: Context): MutableLiveData<News> {

            val allNews = MutableLiveData<News>()
            val apiClientInterface: ApiClientInterface = ApiClient.retrofit.create(ApiClientInterface::class.java)
            val news: Call<News> = apiClientInterface.getAllNewsByCountry(country_code)
            val newsDao: NewsDao = NewsDatabaseInstance.getDbInstance(context).dao()

            news.enqueue(object : Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {}

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    InsertNewsAsync(newsDao).execute(response.body())
                    NewsDatabaseInstance.getDbInstance(context).dao().insert(response.body()!!)
                    allNews.value = response.body()
                }
            })
            return allNews
        }


    inner class InsertNewsAsync(private val newsDao: NewsDao?) : AsyncTask<News, Void, Void>() {

        override fun doInBackground(vararg news: News): Void? {
            Log.d("TAGGG", "inserting data")
            newsDao?.insert(news[0])
            return null
        }
    }
}