package com.example.news

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.news.databinding.ActivityMainBinding
import com.example.news.ui.home_news.HomeNewsFragment
import com.example.news.ui.search_news.SearchNewsFragment
import com.lapism.searchview.Search

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val query = Bundle()
        val searchFragment = SearchNewsFragment()
        val homeFragment = HomeNewsFragment()

        // Initial Value of Search Query
        this.getPreferences(Context.MODE_PRIVATE)?.edit()?.putString(KEY, null)?.apply()


        val mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                this.lifecycleOwner = this@MainActivity
                this.viewmodel = mainActivityViewModel
            }

        // Setting Default fragment to HomeNewsFragment
        changeFragment(homeFragment)

        binding.searchBar.setOnQueryTextListener(object : Search.OnQueryTextListener {
            override fun onQueryTextSubmit(queryText: CharSequence?): Boolean {
                if (queryText.isNullOrBlank()) {
                  changeFragment(homeFragment)
                } else {

                    binding.searchBar.close()
                    query.putString("QUERY", queryText as String?)
                    searchFragment.arguments = query
                    changeFragment(searchFragment)
                }
                return false
            }

            override fun onQueryTextChange(newText: CharSequence?) {
                if(newText.isNullOrBlank()) {
//                    changeFragment(homeFragment)
                }
            }

        })
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}
