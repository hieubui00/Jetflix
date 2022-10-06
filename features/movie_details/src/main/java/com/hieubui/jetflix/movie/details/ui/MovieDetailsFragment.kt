package com.hieubui.jetflix.movie.details.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hieubui.jetflix.core.data.model.Company
import com.hieubui.jetflix.core.data.model.movie.credits.Cast
import com.hieubui.jetflix.core.data.model.movie.credits.Crew
import com.hieubui.jetflix.movie.details.R.string
import com.hieubui.jetflix.movie.details.inject.component.DaggerMovieDetailsComponent
import com.hieubui.jetflix.movie.details.ui.component.BackButton
import com.hieubui.jetflix.movie.details.ui.component.Backdrop
import com.hieubui.jetflix.movie.details.ui.component.CastCard
import com.hieubui.jetflix.movie.details.ui.component.CrewCard
import com.hieubui.jetflix.movie.details.ui.component.MoreButton
import com.hieubui.jetflix.movie.details.ui.component.PosterCard
import com.hieubui.jetflix.movie.details.ui.component.ProductionCompanyCard
import com.hieubui.jetflix.movie.details.ui.component.RatingBar
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import com.hieubui.jetflix.resources.util.setLightStatusBar
import com.hieubui.jetflix.resources.util.toString
import com.hieubui.jetflix.ui.main.MainActivity
import com.hieubui.jetflix.util.ViewModelFactory
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {
    private val model by viewModels<MovieDetailsViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<MovieDetailsViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectComponents()
    }

    private fun injectComponents() {
        val args by navArgs<MovieDetailsFragmentArgs>()
        val mainComponent = (activity as MainActivity).component

        DaggerMovieDetailsComponent.builder()
            .mainComponent(mainComponent)
            .savedStateHandle(args.toSavedStateHandle())
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
                this@MovieDetailsFragment.Content(
                    modifier = Modifier
                        .background(colors.surface)
                        .navigationBarsPadding()
                )
            }
        }
    }

    @Composable
    private fun Content(modifier: Modifier = Modifier) {
        val movieDetails by model.movieDetails.observeAsState()
        val scrollState = rememberScrollState()

        if (movieDetails == null) {
            Loading(modifier)
            return
        }

        Column(modifier = modifier.verticalScroll(scrollState)) {
            Box(modifier = Modifier.zIndex(zIndex = 2f)) {
                Backdrop(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    data = movieDetails?.backdrop
                )

                BackButton(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(all = 16.dp)
                        .size(size = 24.dp),
                    onClick = this@MovieDetailsFragment::back
                )

                PosterCard(
                    modifier = Modifier
                        .width(160.dp)
                        .padding(top = 128.dp)
                        .align(alignment = BottomCenter),
                    data = movieDetails?.poster
                )
            }

            Text( // Title
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .align(alignment = CenterHorizontally),
                fontSize = 24.sp,
                fontWeight = Bold,
                color = colors.onSurface,
                text = movieDetails?.title.orEmpty()
            )

            movieDetails?.originalTitle?.let {
                Text( // Original title
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(alignment = CenterHorizontally),
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = colors.onSurface,
                    text = "($it)"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val releaseDate = movieDetails?.releaseDate?.toString("yyyy-MM-dd").orEmpty()
                val duration = movieDetails?.duration?.let { "$it min" }.orEmpty()
                val voteAvg = movieDetails?.voteAverage?.toString().orEmpty()
                val voteCount = movieDetails?.voteCount?.toString().orEmpty()
                val data = mapOf(
                    stringResource(string.release_date) to releaseDate,
                    stringResource(string.duration) to duration,
                    stringResource(string.vote_avg) to voteAvg,
                    stringResource(string.vote_count) to voteCount,
                )

                data.entries.forEach {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = spacedBy(4.dp)
                    ) {
                        Text(
                            fontSize = 16.sp,
                            color = colors.onSurface,
                            text = it.key
                        )

                        Text(
                            fontSize = 14.sp,
                            fontWeight = Bold,
                            color = colors.onSurface,
                            text = it.value
                        )
                    }
                }
            }

            RatingBar(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(alignment = CenterHorizontally),
                tint = Color(0xFFFFDE17),
                max = 10.0f,
                rating = movieDetails?.voteAverage ?: 0.0f
            )

            movieDetails?.overview?.takeIf { it.isNotBlank() }?.let {
                Text( // Overview
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .padding(horizontal = 16.dp),
                    fontSize = 16.sp,
                    color = colors.onSurface,
                    text = it
                )
            }

            movieDetails?.credits?.casts?.takeIf { it.isNotEmpty() }?.let {
                CastsSection(
                    label = stringResource(string.casts),
                    casts = it
                )
            }

            movieDetails?.credits?.crews?.takeIf { it.isNotEmpty() }?.let {
                CrewsSection(
                    label = stringResource(string.crews),
                    crews = it
                )
            }

            movieDetails?.productionCompanies?.takeIf { it.isNotEmpty() }?.let {
                ProductionCompaniesSection(
                    label = stringResource(string.production_companies),
                    productionCompanies = it
                )
            }
        }
    }

    @Composable
    private fun Loading(modifier: Modifier = Modifier) {
        Box(modifier = modifier) {
            BackButton(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(all = 16.dp)
                    .size(size = 24.dp),
                onClick = this@MovieDetailsFragment::back
            )

            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .align(alignment = Alignment.Center)
            )
        }
    }

    private fun back() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    @Composable
    private fun CastsSection(label: String, casts: List<Cast>) {
        Text( // Label
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colors.onSurface,
            text = label
        )

        LazyRow(
            modifier = Modifier.padding(vertical = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = spacedBy(space = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val limit = 16

            items(items = casts.take(limit)) { cast ->
                CastCard(
                    modifier = Modifier.width(128.dp),
                    cast = cast
                )
            }

            if (casts.size > limit) {
                item {
                    MoreButton { }
                }
            }
        }
    }

    @Composable
    private fun CrewsSection(label: String, crews: List<Crew>) {
        Text( // Label
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colors.onSurface,
            text = label
        )

        LazyRow(
            modifier = Modifier.padding(vertical = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = spacedBy(space = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val limit = 16

            items(items = crews.take(limit)) { crew ->
                CrewCard(
                    modifier = Modifier.width(128.dp),
                    crew = crew
                )
            }

            if (crews.size > limit) {
                item {
                    MoreButton { }
                }
            }
        }
    }

    @Composable
    private fun ProductionCompaniesSection(label: String, productionCompanies: List<Company>) {
        Text( // Label
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = colors.onSurface,
            text = label
        )

        LazyRow(
            modifier = Modifier.padding(vertical = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = spacedBy(space = 16.dp)
        ) {
            items(items = productionCompanies) { productionCompany ->
                ProductionCompanyCard(
                    modifier = Modifier.size(
                        width = 192.dp,
                        height = 128.dp
                    ),
                    company = productionCompany
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLightStatusBar(false)
    }
}
