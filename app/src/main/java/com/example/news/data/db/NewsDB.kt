package com.example.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news.data.model.News
import com.example.news.data.model.NewsItemTypeConverter
import com.example.news.data.model.SourceTypeConverter

@Database(entities = arrayOf(News::class), version = 1, exportSchema = false)
@TypeConverters(NewsItemTypeConverter::class, SourceTypeConverter::class)
abstract class NewsDB : RoomDatabase() {
    abstract fun dao(): NewsDao
}