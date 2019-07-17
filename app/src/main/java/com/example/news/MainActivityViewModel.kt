package com.example.news

import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainActivityViewModel : ViewModel() {

    var searchText: LiveData<String> = MutableLiveData<String>()

    @BindingAdapter("android:onQueryTextChange")
    fun search(view: EditText, search: LiveData<String>) {
        searchText = search
    }


}