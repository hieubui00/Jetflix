package com.hieubui.jetflix.core.domain.usecase

import com.google.gson.Gson
import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import com.hieubui.jetflix.core.data.remote.response.MovieCreditsResponse
import com.hieubui.jetflix.core.data.remote.response.MovieDetailsResponse
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
internal class GetMovieDetailsUseCaseTester {
    @MockK
    private lateinit var theMovieDatabaseService: TheMovieDatabaseService

    @Before
    fun setup() {
        MockKAnnotations.init(this@GetMovieDetailsUseCaseTester)
    }

    private fun setupGetDiscoverMoviesUseCase(scheduler: TestCoroutineScheduler): GetMovieDetailsUseCase {
        val dispatcher = StandardTestDispatcher(scheduler)
        val movieRepository = MovieRepositoryImpl(theMovieDatabaseService, dispatcher)

        return GetMovieDetailsUseCase(movieRepository, dispatcher)
    }

    @Test
    fun `When movie ID has found then return movie credits data has same as response`() = runTest {
        val getMovieDetailsUseCase = setupGetDiscoverMoviesUseCase(testScheduler)
        val movieId = 985939
        val movieDetailsResponse = mockResponse<MovieDetailsResponse>("movie_details.json")
        val movieCreditsResponse = mockResponse<MovieCreditsResponse>("movie_credits.json")

        coEvery { theMovieDatabaseService.getMovieDetails(movieId, any()) }.returns(movieDetailsResponse)
        coEvery { theMovieDatabaseService.getMovieCredits(movieId, any()) }.returns(movieCreditsResponse)

        val result = getMovieDetailsUseCase(movieId)

        assert(result.movieId == movieId)
        assert(result.title == movieDetailsResponse.title)
        assert(result.originalTitle == movieDetailsResponse.originalTitle)
        assert(result.releaseDate == movieDetailsResponse.releaseDate)
        assert(result.overview == movieDetailsResponse.overview)
        assert(result.credits?.casts?.size == movieCreditsResponse.casts?.size)
        assert(result.credits?.crews?.size == movieCreditsResponse.crews?.size)
        assert(result.credits?.casts?.map { it.castId }?.containsAll(movieCreditsResponse.casts?.map { it.castId } ?: listOf()) == true)
        assert(result.credits?.crews?.map { it.crewId }?.containsAll(movieCreditsResponse.crews?.map { it.crewId } ?: listOf()) == true)
        assert(result.voteAverage == movieDetailsResponse.voteAverage)
        assert(result.voteCount == movieDetailsResponse.voteCount)
    }

    private inline fun <reified T> mockResponse(fileName: String): T {
        val responseJSON = javaClass.classLoader
            ?.getResourceAsStream("data/remote/response/$fileName")
            ?.bufferedReader()
            ?.use { it.readText() }

        return Gson().fromJson(responseJSON, T::class.java)
    }

    @Test(expected = HttpException::class)
    fun `When movie ID not found then throws an HttpException`() = runTest {
        val getMovieDetailsUseCase = setupGetDiscoverMoviesUseCase(testScheduler)
        val movieId = -1
        val responseBody = ResponseBody.create(null, "HTTP 404")
        val response = Response.error<Nothing>(404, responseBody)
        val exception = HttpException(response)

        coEvery { theMovieDatabaseService.getMovieDetails(movieId, any()) }.throws(exception)
        coEvery { theMovieDatabaseService.getMovieCredits(movieId, any()) }.throws(exception)
        getMovieDetailsUseCase(movieId)
    }
}