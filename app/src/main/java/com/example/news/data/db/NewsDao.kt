package com.example.news.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.news.data.model.News

@Dao
interface NewsDao {

    @Insert
    fun insert(news: News)

    @Update
    fun update(news: News)

    @Query("select * from news")
    fun selectAllNews(): LiveData<News>
}