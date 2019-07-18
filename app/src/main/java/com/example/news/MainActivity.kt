package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                this.lifecycleOwner = this@MainActivity
                this.viewmodel = mainActivityViewModel
            }

        mainActivityViewModel.getSearchQuery.observe(this, Observer {

        })

    }
}
