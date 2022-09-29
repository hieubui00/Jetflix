package com.hieubui.jetflix.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.hieubui.jetflix.home.ui.component.ActionBar
import com.hieubui.jetflix.home.ui.component.movie_card.MovieCard
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        fitsSystemWindows = true
        setContent {
            JetflixTheme {
                this@HomeFragment.Content()
            }
        }
    }

    @Composable
    private fun Content(modifier: Modifier = Modifier) {
        Surface(modifier = modifier) {
            Column {
                ActionBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 56.dp)
                        .background(Color.White)
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    columns = GridCells.Fixed(count = 2),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(count = 20) {
                        MovieCard()
                    }
                }
            }
        }
    }
}