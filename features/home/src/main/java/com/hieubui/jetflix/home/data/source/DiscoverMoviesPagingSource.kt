package com.hieubui.jetflix.home.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.core.domain.usecase.GetDiscoverMoviesUseCase
import com.hieubui.jetflix.core.util.SortBy
import com.hieubui.jetflix.core.util.SortOrder
import com.hieubui.jetflix.home.injection.scope.HomeScope
import timber.log.Timber
import javax.inject.Inject

@HomeScope
class DiscoverMoviesPagingSource @Inject constructor(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
) : PagingSource<Int, Movie>() {
    var sortBy: SortBy = SortBy.POPULARITY

    var sortOrder: SortOrder = SortOrder.DESCENDING

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> = try {
        val page = params.key ?: 1
        val response = getDiscoverMoviesUseCase(page = page, sortBy = sortBy, sortOrder = sortOrder)

        LoadResult.Page(
            data = response,
            prevKey = null,
            nextKey = page.takeIf { it <= 500 && response.isNotEmpty() }?.plus(1)
        )
    } catch (e: Exception) {
        Timber.e(e)
        LoadResult.Error(e)
    }
}
