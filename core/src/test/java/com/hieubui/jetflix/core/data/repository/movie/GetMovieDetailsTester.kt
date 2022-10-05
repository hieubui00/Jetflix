package com.hieubui.jetflix.core.data.repository.movie

import com.google.gson.Gson
import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import com.hieubui.jetflix.core.data.remote.response.MovieDetailsResponse
import com.hieubui.jetflix.core.data.repository.MovieRepository
import com.hieubui.jetflix.core.data.repository.MovieRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class GetMovieDetailsTester {
    @MockK
    private lateinit var theMovieDatabaseService: TheMovieDatabaseService

    @Before
    fun setup() {
        MockKAnnotations.init(this@GetMovieDetailsTester)
    }

    private fun setupMovieRepository(scheduler: TestCoroutineScheduler): MovieRepository {
        val dispatcher = StandardTestDispatcher(scheduler)

        return MovieRepositoryImpl(theMovieDatabaseService, dispatcher)
    }

    @Test
    fun `When movie ID has found then return movie details data has same as response`() = runTest {
        val movieRepository = setupMovieRepository(testScheduler)
        val movieId = 985939
        val response = mockResponse<MovieDetailsResponse>("data/remote/response/movie_details.json")

        coEvery { theMovieDatabaseService.getMovieDetails(movieId, any()) }.returns(response)

        val result = movieRepository.getMovieDetails(movieId)

        assert(result.movieId == movieId)
        assert(result.title == response.title)
        assert(result.originalTitle == response.originalTitle)
        assert(result.releaseDate == response.releaseDate)
        assert(result.overview == response.overview)
        assert(result.backdropPath == response.backdropPath)
        assert(result.posterPath == response.posterPath)
        assert(result.voteAverage == response.voteAverage)
        assert(result.voteCount == response.voteCount)
    }

    @Test(expected = HttpException::class)
    fun `When movie ID not found then throws an HttpException`() = runTest {
        val movieRepository = setupMovieRepository(testScheduler)
        val movieId = -1
        val responseBody = ResponseBody.create(null, "HTTP 404")
        val response = Response.error<Nothing>(404, responseBody)
        val exception = HttpException(response)

        coEvery { theMovieDatabaseService.getMovieDetails(movieId, any()) }.throws(exception)
        movieRepository.getMovieDetails(movieId)
    }

    private inline fun <reified T> mockResponse(fileName: String): T {
        val responseJSON = javaClass.classLoader
            ?.getResourceAsStream(fileName)
            ?.bufferedReader()
            ?.use { it.readText() }

        return Gson().fromJson(responseJSON, T::class.java)
    }
}