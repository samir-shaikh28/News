package com.example.news.ui.home_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.app.Application
import android.content.Context


class HomeViewModelFactory : ViewModelProvider.Factory {

    lateinit var app: Context
    lateinit var param: String

    constructor(application: Application, param: String) {
        this.app = application
        this.param = param
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}