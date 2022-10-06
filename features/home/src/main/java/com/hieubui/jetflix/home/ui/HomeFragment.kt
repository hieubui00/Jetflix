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
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.home.injection.component.DaggerHomeComponent
import com.hieubui.jetflix.home.ui.component.ActionBar
import com.hieubui.jetflix.home.ui.component.FilterBottomSheet
import com.hieubui.jetflix.home.ui.component.FilterButton
import com.hieubui.jetflix.home.ui.component.LoadingIndicator
import com.hieubui.jetflix.home.ui.component.movie_card.MovieCard
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import com.hieubui.jetflix.resources.util.setLightStatusBar
import com.hieubui.jetflix.ui.main.MainActivity
import com.hieubui.jetflix.util.ViewModelFactory
import kotlinx.coroutines.launch
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

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            JetflixTheme {
                val sheetState = rememberModalBottomSheetState(initialValue = Hidden)
                val coroutineScope = rememberCoroutineScope()

                ModalBottomSheetLayout(
                    modifier = Modifier.fillMaxSize(),
                    sheetState = sheetState,
                    sheetShape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    ),
                    sheetElevation = 8.dp,
                    sheetContent = {
                        FilterBottomSheet(
                            onClose = { coroutineScope.launch { sheetState.hide() } },
                            onSelectedSortByChange = { model.setSortBy(it) },
                            onSelectedSortOrderChange = { model.setSortOrder(it) }
                        )
                    },
                    content = {
                        this@HomeFragment.Content(
                            modifier = Modifier
                                .background(colors.surface)
                                .navigationBarsPadding(),
                            onFilter = { coroutineScope.launch { sheetState.show() } }
                        )
                    }
                )
            }
        }
    }

    @ExperimentalMaterialApi
    @Composable
    private fun Content(
        modifier: Modifier = Modifier,
        onFilter: () -> Unit
    ) {
        Scaffold(
            modifier = modifier,
            topBar = { ActionBar() },
            floatingActionButton = { FilterButton(onClick = onFilter) },
            backgroundColor = colors.background,
            content = { padding ->
                val movies = model.movies.collectAsLazyPagingItems()

                when (movies.loadState.refresh) {
                    is LoadState.Loading -> {
                        Box(modifier = modifier.fillMaxSize()) {
                            LoadingIndicator(
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(alignment = Center)
                            )
                        }
                    }

                    is LoadState.Error -> {
                    }

                    else -> MovieList(
                        modifier = Modifier.padding(padding),
                        movies = movies
                    )
                }
            }
        )
    }

    @Composable
    private fun MovieList(
        modifier: Modifier = Modifier,
        movies: LazyPagingItems<Movie>
    ) {
        LazyVerticalGrid(
            modifier = modifier.padding(horizontal = 16.dp),
            columns = Adaptive(minSize = 128.dp),
            horizontalArrangement = spacedBy(space = 12.dp),
            verticalArrangement = spacedBy(space = 12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(count = movies.itemCount) { index ->
                MovieCard(
                    movie = movies[index]!!,
                    onClick = { navigateToMovieDetails(movies[index]!!) }
                )
            }

            if (movies.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(currentLineSpan = Int.MAX_VALUE) }) {
                    Box(modifier = Modifier.padding(vertical = 8.dp)) {
                        LoadingIndicator(
                            modifier = Modifier
                                .size(48.dp)
                                .align(alignment = Center)
                        )
                    }
                }
            }
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
