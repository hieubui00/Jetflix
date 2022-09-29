package com.hieubui.jetflix.core.data.remote.request

import com.hieubui.jetflix.core.data.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDatabaseService {

    @GET("/3/discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") page: Int = 1
    ): MoviesResponse
}