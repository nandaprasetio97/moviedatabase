package com.nandaprasetio.moviedatabase.presentation.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration.PagingRecyclerViewFragmentConfiguration
import com.nandaprasetio.moviedatabase.core.presentation.epoxycontroller.BasePagingDataEpoxyController
import com.nandaprasetio.moviedatabase.core.presentation.modelvalue.BaseModelValue
import com.nandaprasetio.moviedatabase.presentation.epoxycontroller.MovieGenrePagingDataEpoxyController
import com.nandaprasetio.moviedatabase.core.viewmodel.MovieGenreViewModel
import com.nandaprasetio.moviedatabase.core.viewmodel.PagingDataViewModel
import com.nandaprasetio.moviedatabase.presentation.Constant
import com.nandaprasetio.moviedatabase.presentation.activity.DiscoveredMovieBasedGenreActivity
import com.nandaprasetio.moviedatabase.presentation.misc.pagingrecyclerviewfragmentconfiguration.copy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ObsoleteCoroutinesApi
@AndroidEntryPoint
class MovieGenrePagingRecyclerViewFragment: BasePagingRecyclerViewFragment<BaseModelValue>() {
    private val movieGenreViewModel: MovieGenreViewModel by viewModels()

    override fun getPagingDataEpoxyController(): BasePagingDataEpoxyController<BaseModelValue> {
        return MovieGenrePagingDataEpoxyController { context, genre ->
            Intent(context, DiscoveredMovieBasedGenreActivity::class.java).also { intent ->
                intent.putExtra(Constant.ARGUMENT_GENRE_ID, genre.id.toInt())
                context.startActivity(intent)
            }
        }
    }

    override fun getPagingDataViewModel(): PagingDataViewModel {
        return movieGenreViewModel
    }

    override fun getPagingDataFlow(): Flow<PagingData<BaseModelValue>> {
        return movieGenreViewModel.getMovieGenrePagingDataFlow()
    }

    override fun getPagingRecyclerViewFragmentConfiguration(): PagingRecyclerViewFragmentConfiguration {
        return super.getPagingRecyclerViewFragmentConfiguration().copy(spanCount = 2)
    }
}