package com.hieubui.jetflix.core.data.repository.movie

import com.google.gson.Gson
import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import com.hieubui.jetflix.core.data.remote.response.MovieCreditsResponse
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
internal class GetMovieCreditsTester {

    @MockK
    private lateinit var theMovieDatabaseService: TheMovieDatabaseService

    @Before
    fun setup() {
        MockKAnnotations.init(this@GetMovieCreditsTester)
    }

    private fun setupMovieRepository(scheduler: TestCoroutineScheduler): MovieRepository {
        val dispatcher = StandardTestDispatcher(scheduler)

        return MovieRepositoryImpl(theMovieDatabaseService, dispatcher)
    }

    @Test
    fun `When movie ID has found then return movie credits data has same as response`() = runTest {
        val movieRepository = setupMovieRepository(testScheduler)
        val movieId = 985939
        val response = mockResponse<MovieCreditsResponse>("data/remote/response/movie_credits.json")

        coEvery { theMovieDatabaseService.getMovieCredits(movieId, any()) }.returns(response)

        val result = movieRepository.getMovieCredits(movieId)

        assert(result.movieId == movieId)
        assert(result.casts?.size == response.casts?.size)
        assert(result.crews?.size == response.crews?.size)
        assert(result.casts?.containsAll(response.casts ?: listOf()) == true)
        assert(result.crews?.containsAll(response.crews ?: listOf()) == true)
    }

    private inline fun <reified T> mockResponse(fileName: String): T {
        val responseJSON = javaClass.classLoader
            ?.getResourceAsStream(fileName)
            ?.bufferedReader()
            ?.use { it.readText() }

        return Gson().fromJson(responseJSON, T::class.java)
    }

    @Test(expected = HttpException::class)
    fun `When movie ID not found then throws an HttpException`() = runTest {
        val movieRepository = setupMovieRepository(testScheduler)
        val movieId = -1
        val responseBody = ResponseBody.create(null, "HTTP 404")
        val response = Response.error<Nothing>(404, responseBody)
        val exception = HttpException(response)

        coEvery { theMovieDatabaseService.getMovieCredits(movieId, any()) }.throws(exception)
        movieRepository.getMovieCredits(movieId)
    }
}
