package com.example.news.ui.home_news


import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
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

    lateinit var adapter: HomeNewsAdapter

    lateinit var binding: FragmentHomeNewsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_news, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   binding.home_news

        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        val allNewsViewModel = ViewModelProviders.of(this).get(HomeNewsViewModel::class.java)

        if (allNewsViewModel.allNews.value == null) {
            allNewsViewModel.getNewsByCountry(context!!.applicationContext, "in")
        }

        allNewsViewModel.allNews.observe(this, Observer { response ->
            adapter = HomeNewsAdapter(response.articles)
            binding.homeNews.adapter = adapter
        })
    }
}
