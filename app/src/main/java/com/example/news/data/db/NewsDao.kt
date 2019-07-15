package com.example.news.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.data.model.News

@Dao
public interface NewsDao {

    @Insert
    fun insert(news: News)

    @Update
    fun update(news: News)


    @Query("select * from news")
    fun selectAllNews(): LiveData<List<News>>
}