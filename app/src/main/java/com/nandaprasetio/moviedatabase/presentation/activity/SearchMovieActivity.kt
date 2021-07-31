package com.nandaprasetio.moviedatabase.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.nandaprasetio.moviedatabase.R
import com.nandaprasetio.moviedatabase.core.presentation.BaseActivity
import com.nandaprasetio.moviedatabase.databinding.ActivitySearchMovieBinding
import com.nandaprasetio.moviedatabase.presentation.fragment.SearchMoviePagingRecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ObsoleteCoroutinesApi


@ObsoleteCoroutinesApi
@AndroidEntryPoint
class SearchMovieActivity: BaseActivity<ActivitySearchMovieBinding>() {
    private var searchMoviePagingRecyclerViewFragment: SearchMoviePagingRecyclerViewFragment? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchMoviePagingRecyclerViewFragment = this.supportFragmentManager
            .findFragmentById(R.id.fragment_container_view_search_movie) as SearchMoviePagingRecyclerViewFragment
        this.supportActionBar?.also { configureActionBar(it) }
    }

    private fun configureActionBar(actionBar: ActionBar) {
        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setDisplayShowCustomEnabled(true)
        actionBar.setCustomView(R.layout.layout_view_search)
        val toolbar: Toolbar = actionBar.customView.parent as Toolbar
        val buttonBack: AppCompatImageButton = toolbar.findViewById(R.id.button_back)
        buttonBack.isVisible = onGetActivityConfiguration().supportBack
        buttonBack.setOnClickListener { super.onBackPressed() }
        toolbar.setContentInsetsAbsolute(0, 0)
        toolbar.setPadding(0, 0, 0, 0)
        changeViewSettings(actionBar.customView)
    }

    private fun changeViewSettings(actionBarCustomView: View) {
        val editTextSearch: EditText = actionBarCustomView.findViewById(R.id.edit_text_search)
        editTextSearch.requestFocus()
        editTextSearch.setOnEditorActionListener { textView, actionId, keyEvent ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchMoviePagingRecyclerViewFragment?.command?.searchMovie(editTextSearch.text.toString())
                    val imm: InputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    val view: View? = this.currentFocus
                    if (view != null) { imm.hideSoftInputFromWindow(view.windowToken, 0) }
                    editTextSearch.clearFocus()
                    true
                }
                else -> false
            }
        }
    }

    override fun onGetViewBinding(): ActivitySearchMovieBinding {
        return ActivitySearchMovieBinding.inflate(this.layoutInflater)
    }

    override fun onDestroy() {
        searchMoviePagingRecyclerViewFragment = null
        super.onDestroy()
    }
}