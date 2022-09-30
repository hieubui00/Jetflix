package com.hieubui.jetflix.home.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hieubui.jetflix.home.R
import com.hieubui.jetflix.home.injection.component.DaggerHomeComponent
import com.hieubui.jetflix.home.ui.component.ActionBar
import com.hieubui.jetflix.home.ui.component.movie_card.MovieCard
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import com.hieubui.jetflix.ui.main.MainActivity
import com.hieubui.jetflix.util.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {
    private val viewModel by viewModels<HomeViewModel> { factory }

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
        fitsSystemWindows = true
        setContent {
            JetflixTheme {
                Scaffold(
                    topBar = {
                        ActionBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(height = 56.dp)
                                .background(Color.White)
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            backgroundColor = MaterialTheme.colors.primary,
                            onClick = { }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_filter),
                                contentDescription = stringResource(id = R.string.filter)
                            )
                        }
                    },
                    content = { this@HomeFragment.Content(modifier = Modifier.padding(it)) }
                )
            }
        }
    }

    @Composable
    private fun Content(modifier: Modifier = Modifier) {
        val movie by viewModel.movies.observeAsState(null)

        movie?.let {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(modifier)
                    .padding(horizontal = 16.dp),
                columns = GridCells.Adaptive(minSize = 128.dp),
                horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(items = it) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = { }
                    )
                }
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .align(alignment = Alignment.Center)
            )
        }
    }
}