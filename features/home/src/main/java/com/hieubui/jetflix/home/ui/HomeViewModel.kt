package com.hieubui.jetflix.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.core.util.SortBy
import com.hieubui.jetflix.core.util.SortOrder
import com.hieubui.jetflix.home.data.source.DiscoverMoviesPagingSource
import com.hieubui.jetflix.home.injection.scope.HomeScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HomeScope
class HomeViewModel @Inject constructor(
    private val discoverMoviesPagingSource: DiscoverMoviesPagingSource
) : ViewModel() {
    private val _sortOptions = MutableLiveData<Pair<SortBy, SortOrder>>()

    val movies: Flow<PagingData<Movie>>

    init {
        val defaultSortOptions = Pair(SortBy.POPULARITY, SortOrder.DESCENDING)
        movies = _sortOptions.switchMap {
            val pagingConfig = PagingConfig(pageSize = 16)
            val pagingSource = discoverMoviesPagingSource.apply {
                sortBy = it.first
                sortOrder = it.second
            }

            return@switchMap Pager(pagingConfig) { pagingSource }
                .liveData
                .cachedIn(viewModelScope)
        }.asFlow()

        _sortOptions.postValue(defaultSortOptions)
    }

    fun setSortBy(sortBy: SortBy) {
        val sortOptions = _sortOptions.value!!.copy(first = sortBy)

        _sortOptions.postValue(sortOptions)
    }

    fun setSortOrder(sortOrder: SortOrder) {
        val sortOptions = _sortOptions.value!!.copy(second = sortOrder)

        _sortOptions.postValue(sortOptions)
    }
}
