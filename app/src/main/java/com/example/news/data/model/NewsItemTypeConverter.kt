package com.example.news.data.model

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson


class NewsItemTypeConverter {

    companion object {
        private val gson = Gson()
        private val type = object : TypeToken<List<Source>>() {
        }.type

        @TypeConverter
        @JvmStatic
        fun stringToNestedData(json: String?): List<Source>? {
            return if (json == null) {
                null
            } else gson.fromJson<List<Source>>(json, type)
        }

        @TypeConverter
        @JvmStatic
        fun nestedDataToString(nestedData: List<Source>?): String? {
            return if (nestedData == null) {
                null
            } else gson.toJson(nestedData, type)
        }
    }
}