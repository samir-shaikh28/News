package com.example.news.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news.data.model.Source

@Entity(tableName = "news")
data class News (
    @PrimaryKey val _id: Int,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "totalResults") val totalResults: String,
    @ColumnInfo(name = "articles") val articles: List<NewsItem>
    )