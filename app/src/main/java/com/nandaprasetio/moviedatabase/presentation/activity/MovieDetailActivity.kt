package com.nandaprasetio.moviedatabase.presentation.activity

import android.os.Bundle
import com.nandaprasetio.moviedatabase.R
import com.nandaprasetio.moviedatabase.core.misc.ActivityConfiguration
import com.nandaprasetio.moviedatabase.core.presentation.BaseActivity
import com.nandaprasetio.moviedatabase.databinding.ActivityDetailMovieBinding
import com.nandaprasetio.moviedatabase.presentation.Constant
import com.nandaprasetio.moviedatabase.presentation.fragment.MovieDetailPagingRecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@AndroidEntryPoint
class MovieDetailActivity: BaseActivity<ActivityDetailMovieBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding?.also {
            val genreId: Int = this.intent.getIntExtra(Constant.ARGUMENT_MOVIE_ID, 0)
            val discoveredMovieBasedGenrePagingRecyclerViewFragment = this.supportFragmentManager.findFragmentById(
                it.fragmentContainerViewDetailMovie.id
            ) as MovieDetailPagingRecyclerViewFragment
            discoveredMovieBasedGenrePagingRecyclerViewFragment.arguments = Bundle().also { bundle ->
                bundle.putInt(Constant.ARGUMENT_MOVIE_ID, genreId)
            }
        }
    }

    override fun onGetActivityConfiguration(): ActivityConfiguration {
        return super.onGetActivityConfiguration().copy(titleResId = R.string.title_movie_detail)
    }

    override fun onGetViewBinding(): ActivityDetailMovieBinding {
        return ActivityDetailMovieBinding.inflate(this.layoutInflater)
    }
}