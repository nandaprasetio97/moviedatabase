package com.nandaprasetio.moviedatabase.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.nandaprasetio.moviedatabase.R
import com.nandaprasetio.moviedatabase.core.misc.ActivityConfiguration
import com.nandaprasetio.moviedatabase.core.presentation.BaseActivity
import com.nandaprasetio.moviedatabase.databinding.ActivityMainBinding
import com.nandaprasetio.moviedatabase.presentation.misc.bottomnavcomponent.BottomNavManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ObsoleteCoroutinesApi

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {
    private var bottomNavManager: BottomNavManager? = null

    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        this.setTheme(R.style.Theme_MovieDatabase)
        super.onCreate(savedInstanceState)
        viewBinding?.also {
            setupNavigationManager(it)
        }
    }

    private fun setupNavigationManager(activityMainBinding: ActivityMainBinding) {
        bottomNavManager?.setupNavController() ?: this.run {
            bottomNavManager = BottomNavManager(
                fragmentManager = this.supportFragmentManager,
                containerId = activityMainBinding.fragmentNavHost.id,
                bottomNavigationView = activityMainBinding.bottomNavigationViewNavHost,
                navGraphIds = listOf(
                    R.navigation.nav_graph_popular_movie,
                    R.navigation.nav_graph_movie_genre,
                    R.navigation.nav_graph_top_rated_movie,
                    R.navigation.nav_graph_upcoming_movie,
                ),
                firstBottomNavigationItemId = R.id.nav_graph_popular_movie
            )
        }
    }

    override fun onGetActivityConfiguration(): ActivityConfiguration {
        return super.onGetActivityConfiguration().copy(supportBack = false)
    }

    override fun onGetViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(this.layoutInflater)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_search_movie -> this.startActivity(Intent(this, SearchMovieActivity::class.java)).let { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bottomNavManager?.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNavManager?.onRestoreInstanceState(savedInstanceState)
        viewBinding?.also {
            setupNavigationManager(it)
        }
    }

    override fun onBackPressed() {
        if (bottomNavManager?.onBackPressed() == false) {
            super.onBackPressed()
        }
    }
}