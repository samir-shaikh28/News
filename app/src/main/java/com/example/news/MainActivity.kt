package com.example.news

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Contacts.SettingsColumns.KEY
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.arlib.floatingsearchview.FloatingSearchView
import com.example.news.app.App
import com.example.news.databinding.ActivityMainBinding
import com.example.news.reciever.NetworkConnectionChecker
import com.example.news.ui.home_news.HomeNewsFragment
import com.example.news.ui.search_news.SearchNewsFragment
import com.lapism.searchview.Search
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NetworkConnectionChecker.ConnectivityReceiverListener {

    private var networkStatus: Boolean = false

    private val mIntentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
    private val mNetworkChecker = NetworkConnectionChecker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val query = Bundle()
//        val searchFragment = SearchNewsFragment()
        val homeFragment = HomeNewsFragment()

        // Initial Value of Search Query
        this.getPreferences(Context.MODE_PRIVATE)?.edit()?.putString(KEY, null)?.apply()

        val mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // TODO: Remove binding from Layout File
        // Binding is not being used for updating data
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
                    if (networkStatus) {
                        Log.d("TAGGG", "Submitted: $queryText")
                        binding.searchBar.close()
                        query.putString("QUERY", queryText as String?)
                        val searchFragment = SearchNewsFragment()
                        searchFragment.arguments = query
                        changeFragment(searchFragment)
                    } else {
                        Toast.makeText(this@MainActivity, "You're Offline", Toast.LENGTH_SHORT).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: CharSequence?) {
                if (newText.isNullOrBlank()) {
                    changeFragment(homeFragment)
                }
            }
        })

//        binding.floatingSearchView.setOnQueryChangeListener(object : FloatingSearchView.OnQueryChangeListener{
//            override fun onSearchTextChanged(oldQuery: String?, newQuery: String?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mNetworkChecker, mIntentFilter)
        App.getInstance().setConnectivityListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mNetworkChecker)
    }

    override fun onNetworkConnectionChanged(status: Boolean) {
        changeStatusBarColor(status)
        networkStatus = status
    }

    private fun changeStatusBarColor(status: Boolean) {
        if (status) {
            if (network_status.visibility == View.VISIBLE) {
                network_status.text = resources.getString(R.string.network_connected)
                network_status.setBackgroundColor(resources.getColor(R.color.darkGreen))
                Handler().postDelayed({
                    network_status.visibility = View.GONE
                }, 3000)
                window.statusBarColor = resources.getColor(R.color.colorPrimaryDark)
            }
        } else {
            network_status.visibility = View.VISIBLE
            network_status.text = resources.getString(R.string.network_disconnected)
            network_status.setBackgroundColor(resources.getColor(R.color.red))
            window.statusBarColor = resources.getColor(R.color.red)
        }
    }

}

