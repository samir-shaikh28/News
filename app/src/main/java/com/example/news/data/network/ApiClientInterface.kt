package com.example.news.data.network

import com.example.news.data.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClientInterface {

    @GET("/v2/top-headlines?apiKey=1d91d1196b464554b15cab5ac74c6667")
    fun getAllNewsByCountry(@Query("country") country_code: String): Call<News>
}