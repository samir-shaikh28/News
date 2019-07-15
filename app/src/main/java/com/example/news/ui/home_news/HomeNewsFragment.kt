package com.example.news.ui.home_news


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.news.R
import com.example.news.data.model.News
import kotlinx.android.synthetic.main.fragment_home_news.*


class HomeNewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_news, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var allNewsViewModel = ViewModelProviders.of(this).get(HomeNewsViewModel::class.java)
        allNewsViewModel.allNews.observe(this, Observer { response ->
            Log.d("TAGGGG", "bserving")
//            Log.d("TAGGGG", response.toString())

            text.text = response.articles?.size.toString()
        })
    }


}
