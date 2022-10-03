package com.hieubui.jetflix.movie.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hieubui.jetflix.core.data.repository.MovieRepository
import com.hieubui.jetflix.movie.details.inject.scope.MovieDetailsScope
import javax.inject.Inject

@MovieDetailsScope
class MovieDetailsViewModel @Inject constructor(
    state: SavedStateHandle,

    private val movieRepository: MovieRepository,
) : ViewModel() {

    init {
        val movieId = state.get<Int>(KEY_MOVIE_ID) ?: -1

        getMovieDetails(movieId)
    }

    private fun getMovieDetails(movieId: Int) {

    }

    companion object {
        private const val KEY_MOVIE_ID = "movieId"
    }
}