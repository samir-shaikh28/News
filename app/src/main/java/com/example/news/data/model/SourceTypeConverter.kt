package com.example.news.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SourceTypeConverter {

    companion object {
        private val gson = Gson()
        private val type = object : TypeToken<List<NewsItem>>() {
        }.type

        @TypeConverter
        @JvmStatic
        fun stringToNestedData(json: String?): List<NewsItem>? {
            return if (json == null) {
                null
            } else gson.fromJson<List<NewsItem>>(json, type)
        }

        @TypeConverter
        @JvmStatic
        fun nestedDataToString(nestedData: List<NewsItem>?): String? {
            return if (nestedData == null) {
                null
            } else gson.toJson(nestedData, type)
        }
    }
}