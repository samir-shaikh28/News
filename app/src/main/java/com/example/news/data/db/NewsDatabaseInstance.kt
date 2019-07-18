package com.example.news.data.db

import android.content.Context
import androidx.room.Room

class NewsDatabaseInstance {

    companion object {
        private lateinit var databaseClient: NewsDB
        fun getDbInstance(context: Context): NewsDB {
            if (!::databaseClient.isInitialized) {
                databaseClient = Room.databaseBuilder(context, NewsDB::class.java, "news_item_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return databaseClient
        }

    }
}