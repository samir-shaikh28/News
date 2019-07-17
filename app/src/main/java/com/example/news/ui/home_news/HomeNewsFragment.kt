package com.example.news.ui.home_news


import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.news.R
import com.example.news.databinding.FragmentHomeNewsBinding


class HomeNewsFragment : Fragment() {

    private lateinit var mAdapter: HomeNewsAdapter
    private lateinit var mBinding: FragmentHomeNewsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_news, container, false)
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        * Keep Screen in portrait mode for home screen
        * */
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val allNewsViewModel = ViewModelProviders.of(this).get(HomeNewsViewModel::class.java)

        /*
        * Fetch data from server and update LiveData, if LiveData is null
        * */
        if (allNewsViewModel.allNews.value == null) {
            allNewsViewModel.getNewsByCountry("in")
        }

        /*
        * Observer the live data and keep updating UI
        * */
        allNewsViewModel.allNews.observe(this, Observer { response ->
            mAdapter = HomeNewsAdapter(response.articles)
            mBinding.homeNews.adapter = mAdapter
        })
    }
}
