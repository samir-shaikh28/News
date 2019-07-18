package com.example.news

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val searchText = MutableLiveData<String>()

    var _getSearchQuery = MutableLiveData<String>()

    val getSearchQuery: LiveData<String>
        get() = _getSearchQuery

    fun onSearch() {
        _getSearchQuery.value = searchText.value
    }
}