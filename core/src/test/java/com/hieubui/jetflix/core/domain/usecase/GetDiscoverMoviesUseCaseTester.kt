package com.hieubui.jetflix.core.domain.usecase

import com.google.gson.Gson
import com.hieubui.jetflix.core.data.remote.request.TheMovieDatabaseService
import com.hieubui.jetflix.core.data.remote.response.MoviesResponse
import com.hieubui.jetflix.core.data.repository.MovieRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetDiscoverMoviesUseCaseTester {
    @MockK
    private lateinit var theMovieDatabaseService: TheMovieDatabaseService

    @Before
    fun setup() {
        MockKAnnotations.init(this@GetDiscoverMoviesUseCaseTester)
    }

    private fun setupGetDiscoverMoviesUseCase(scheduler: TestCoroutineScheduler): GetDiscoverMoviesUseCase {
        val dispatcher = StandardTestDispatcher(scheduler)
        val movieRepository = MovieRepositoryImpl(theMovieDatabaseService, dispatcher)

        return GetDiscoverMoviesUseCase(movieRepository, dispatcher)
    }

    @Test
    fun `When page less than 1 then return empty list`() = runTest {
        val getDiscoverMoviesUseCase = setupGetDiscoverMoviesUseCase(testScheduler)
        val testCases = -5..0
        val result = testCases.map { getDiscoverMoviesUseCase(it) }

        assert(result.all { it.isEmpty() })
    }

    @Test
    fun `When page greater than 500 then return empty list`() = runTest {
        val getDiscoverMoviesUseCase = setupGetDiscoverMoviesUseCase(testScheduler)
        val testCases = 501..505
        val result = testCases.map { getDiscoverMoviesUseCase(it) }

        assert(result.all { it.isEmpty() })
    }

    @Test
    fun `When page greater than 0 and less than 500 then return a data list`() = runTest {
        val getDiscoverMoviesUseCase = setupGetDiscoverMoviesUseCase(testScheduler)
        val response = mockResponse<MoviesResponse>("data/remote/response/discover_movies.json")

        coEvery { theMovieDatabaseService.getDiscoverMovies(any()) }.returns(response)

        val testCases = (1..5) + (495..500)
        val result = testCases.map { getDiscoverMoviesUseCase(page = it) }

        assert(result.all { it.size == response.results?.size })
    }

    private inline fun <reified T> mockResponse(fileName: String): T {
        val responseJSON = javaClass.classLoader
            ?.getResourceAsStream(fileName)
            ?.bufferedReader()
            ?.use { it.readText() }

        return Gson().fromJson(responseJSON, T::class.java)
    }
}
