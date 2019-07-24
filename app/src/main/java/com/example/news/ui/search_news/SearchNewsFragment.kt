package com.example.news.ui.search_news


import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
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
import com.example.news.databinding.FragmentSearchNewsBinding
import com.example.news.ui.adapter.NewsItemAdapter
import com.example.news.ui.home_news.HomeNewsViewModel
import kotlinx.android.synthetic.main.fragment_home_news.view.*
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.android.synthetic.main.news_viewpager.*
import kotlinx.android.synthetic.main.news_viewpager.view.*


class SearchNewsFragment : Fragment() {

    private lateinit var mAdapter: NewsItemAdapter
    private lateinit var mBinding: FragmentSearchNewsBinding
    private lateinit var query: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_news, container, false)
        Log.d("TAGGG", "Replaced")
        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            query = arguments!!.getString("QUERY").toString()
            queryText.text = query
        }
        var previousSearchText: String? = activity?.getPreferences(Context.MODE_PRIVATE)?.getString(KEY, null)

        /*
        * Keep Screen in portrait mode for home screen
        * */
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val searchNewsViewModel = ViewModelProviders.of(this).get(SearchNewsViewModel::class.java)

        /*
        * Fetch data from server and update LiveData, if LiveData is null
        * */
        if (searchNewsViewModel.searchNews.value == null || query != previousSearchText ) {
            searchNewsViewModel.getNewsBySearchQuery(query)
        }

        /*
        * Observer the live data and keep updating UI
        * */
        searchNewsViewModel.searchNews.observe(this, Observer { response ->
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




