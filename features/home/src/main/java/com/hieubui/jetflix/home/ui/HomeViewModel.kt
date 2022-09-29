package com.hieubui.jetflix.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hieubui.jetflix.core.data.model.Movie
import com.hieubui.jetflix.core.data.repository.MovieRepository
import com.hieubui.jetflix.home.injection.scope.HomeScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HomeScope
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()

    private var currentPage = 1

    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        getDiscoverMovies(currentPage)
    }

    private fun getDiscoverMovies(page: Int): Job = viewModelScope.launch {
        try {
            val movies = movieRepository.getDiscoverMovies(page)

            _movies.postValue(movies)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}