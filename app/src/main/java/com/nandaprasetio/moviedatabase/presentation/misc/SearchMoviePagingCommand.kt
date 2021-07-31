package com.nandaprasetio.moviedatabase.presentation.misc

class SearchMoviePagingCommand(
    private val onSearchMovie: (String) -> Unit
) {
    fun searchMovie(search: String) {
        onSearchMovie.invoke(search)
    }
}