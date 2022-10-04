package com.hieubui.jetflix.home.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
import androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.home.injection.component.DaggerHomeComponent
import com.hieubui.jetflix.home.ui.component.ActionBar
import com.hieubui.jetflix.home.ui.component.FilterButton
import com.hieubui.jetflix.home.ui.component.movie_card.MovieCard
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import com.hieubui.jetflix.resources.util.setLightStatusBar
import com.hieubui.jetflix.ui.main.MainActivity
import com.hieubui.jetflix.util.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {
    private val model by viewModels<HomeViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<HomeViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectComponents()
    }

    private fun injectComponents() {
        val mainComponent = (activity as? MainActivity)?.component

        DaggerHomeComponent.builder()
            .mainComponent(mainComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            JetflixTheme {
                this@HomeFragment.Content(
                    modifier = Modifier
                        .background(colors.surface)
                        .navigationBarsPadding()
                )
            }
        }
    }

    @Composable
    private fun Content(modifier: Modifier = Modifier) {
        Scaffold(
            modifier = modifier,
            topBar = { ActionBar() },
            floatingActionButton = { FilterButton(onClick = { }) },
            backgroundColor = colors.background,
            content = { padding ->
                val movies by model.movies.observeAsState(null)

                if (movies == null) {
                    Loading(
                        modifier = Modifier
                            .statusBarsPadding()
                            .padding(padding)
                    )
                    return@Scaffold
                }

                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    columns = Adaptive(minSize = 128.dp),
                    horizontalArrangement = spacedBy(space = 12.dp),
                    verticalArrangement = spacedBy(space = 12.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(items = movies!!) { movie ->
                        MovieCard(
                            movie = movie,
                            onClick = { navigateToMovieDetails(movie) }
                        )
                    }
                }
            }
        )
    }

    @Composable
    private fun Loading(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .align(alignment = Center)
            )
        }
    }

    private fun navigateToMovieDetails(movie: Movie) {
        val directions = HomeFragmentDirections.navigateToMovieDetails(movie.movieId ?: -1)

        findNavController().navigate(directions)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nightMode = getDefaultNightMode()

        setLightStatusBar(nightMode == MODE_NIGHT_NO || nightMode == MODE_NIGHT_UNSPECIFIED)
        super.onViewCreated(view, savedInstanceState)
    }
}
