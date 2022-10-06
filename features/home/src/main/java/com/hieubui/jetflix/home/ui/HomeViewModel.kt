package com.hieubui.jetflix.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.home.data.source.DiscoverMoviesPagingSource
import com.hieubui.jetflix.home.injection.scope.HomeScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HomeScope
class HomeViewModel @Inject constructor(
    private val discoverMoviesPagingSource: DiscoverMoviesPagingSource
) : ViewModel() {
    val movies: Flow<PagingData<Movie>>

    init {
        val pagingConfig = PagingConfig(pageSize = 16)

        movies = Pager(pagingConfig) { discoverMoviesPagingSource }
            .flow
            .cachedIn(viewModelScope)
    }
}
