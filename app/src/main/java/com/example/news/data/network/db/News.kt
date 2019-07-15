package com.example.news.data.network.db

data class News (val author: String?,
                 val title: String?,
                 val content: String?,
                 val description: String?,
                 val publishedAt: String?,
                 val urlToImage: String?,
                 val url: String?,
                 val source: Source?)