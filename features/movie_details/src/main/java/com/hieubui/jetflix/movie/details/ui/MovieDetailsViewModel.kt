package com.hieubui.jetflix.movie.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieubui.jetflix.core.data.model.movie.MovieDetails
import com.hieubui.jetflix.core.domain.usecase.GetMovieDetailsUseCase
import com.hieubui.jetflix.movie.details.inject.scope.MovieDetailsScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@MovieDetailsScope
class MovieDetailsViewModel @Inject constructor(
    state: SavedStateHandle,

    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetails>()

    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    init {
        val movieId = state.get<Int>(KEY_MOVIE_ID) ?: -1

        getMovieDetails(movieId)
    }

    private fun getMovieDetails(movieId: Int): Job = viewModelScope.launch {
        try {
            val movieDetails = getMovieDetailsUseCase(movieId)

            _movieDetails.postValue(movieDetails)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    companion object {
        private const val KEY_MOVIE_ID = "movieId"
    }
}
