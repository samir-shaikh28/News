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
import com.example.news.MainActivity
import com.example.news.R
import com.example.news.databinding.FragmentHomeNewsBinding
import com.example.news.ui.adapter.NewsItemAdapter
import kotlinx.android.synthetic.main.fragment_home_news.*
import kotlinx.android.synthetic.main.fragment_home_news.view.*
import kotlinx.android.synthetic.main.news_viewpager.*
import kotlinx.android.synthetic.main.news_viewpager.view.*


class HomeNewsFragment : Fragment() {

    private lateinit var mAdapter: NewsItemAdapter
    private lateinit var mBinding: FragmentHomeNewsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_news, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*
        * Keep Screen in portrait mode for home screen
        * */
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val allNewsViewModel = ViewModelProviders.of(this).get(HomeNewsViewModel::class.java)

        /*
        * Fetch data from server and update LiveData, if LiveData is null
        * */
        if (allNewsViewModel.allNews.value == null ) {
            allNewsViewModel.getNewsByCountry("in", activity!!.applicationContext)
        }


        /*
        * Observer the live data and keep updating UI
        * */
        allNewsViewModel.allNews.observe(this, Observer { response ->
            if (response != null) {
                mAdapter = NewsItemAdapter(response.articles)

                mBinding.root.news_viewpager.home_news.adapter = mAdapter
                if (progressBar.visibility == View.VISIBLE) {
                    progressBar.visibility = View.GONE
                }
            } else {
                if (progressBar.visibility == View.GONE) {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}
