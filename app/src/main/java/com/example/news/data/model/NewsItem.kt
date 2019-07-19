package com.example.news.data.model

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.news.R


@BindingAdapter("new_item_image")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrBlank())
        Glide.with(view.context)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.progress_bar))
            .load(imageUrl)
            .into(view)
}

data class NewsItem(
    @PrimaryKey val _id: Int,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
    @ColumnInfo(name = "urlToImage") val urlToImage: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "source")
    @TypeConverters(SourceTypeConverter::class) val source: Source
)
