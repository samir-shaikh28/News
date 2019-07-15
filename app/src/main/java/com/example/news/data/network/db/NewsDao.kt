package com.example.news.data.network.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface NewsDao {

    @Insert
    fun insert(news: News)

    @Update
    fun update(news: News)


    @Query("select * from news")
    fun selectAllNews(): LiveData<List<News>>
}