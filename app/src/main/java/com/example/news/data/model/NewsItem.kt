package com.example.news.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NewsItem (
    @PrimaryKey val _id: Int,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "source") val source: Source?
)