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

        var allNews: LiveData<News> = MutableLiveData<News>()
        val fetchNewsData = FetchNewsData()

        /**
         * Fetch Data from server
         * @param country_code String
         * @return @Nullable MutableLiveData<News>
         */
        fun fetchNewsByCountry(country_code: String, context: Context): LiveData<News> {

            allNews = NewsDatabaseInstance.getDbInstance(context).dao().selectAllNews()
            Log.d("TAGGG","here" +allNews.value?.status)
            return if(allNews.value != null) {
                Log.d("TAGGG", "db has value")
                allNews
            } else {
                Log.d("TAGGG", "db doesnot have value")
                fetchNewsData.fetchNewsByCountry(country_code, context)
            }




//
//            //  val allNews = MutableLiveData<News>()
//            val apiClientInterface: ApiClientInterface = ApiClient.retrofit.create(ApiClientInterface::class.java)
//            val news: Call<News> = apiClientInterface.getAllNewsByCountry(country_code)
//
//            news.enqueue(object : Callback<News> {
//                override fun onFailure(call: Call<News>, t: Throwable) {}
//
//                override fun onResponse(call: Call<News>, response: Response<News>) {
//                    response.body()?.let { NewsDatabaseInstance.getDbInstance(context).dao().insert(it) }
//                    allNews.value = response.body()
//                }
//            })
//            return allNews
        }
    }
}