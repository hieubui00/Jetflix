package com.hieubui.jetflix.core.data.remote.request

import com.hieubui.jetflix.core.data.remote.response.MovieCreditsResponse
import com.hieubui.jetflix.core.data.remote.response.MovieDetailsResponse
import com.hieubui.jetflix.core.data.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Locale

interface TheMovieDatabaseService {

    @GET("/3/discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = Locale.getDefault().language
    ): MoviesResponse

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = Locale.getDefault().language
    ): MovieDetailsResponse

    @GET("/3/movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = Locale.getDefault().language
    ): MovieCreditsResponse
}
