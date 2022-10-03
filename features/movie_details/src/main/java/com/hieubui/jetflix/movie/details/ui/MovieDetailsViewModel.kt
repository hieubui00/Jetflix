package com.hieubui.jetflix.movie.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hieubui.jetflix.core.data.repository.MovieRepository
import com.hieubui.jetflix.movie.details.inject.scope.MovieDetailsScope
import javax.inject.Inject

@MovieDetailsScope
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,

    private val movieRepository: MovieRepository,
) : ViewModel() {

    init {
        val movieId = savedStateHandle.get<Int>("movieId") ?: -1

        getMovieDetails(movieId)
    }

    private fun getMovieDetails(movieId: Int) {

    }
}