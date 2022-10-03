package com.hieubui.jetflix.movie.details.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat.getInsetsController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hieubui.jetflix.movie.details.inject.component.DaggerMovieDetailsComponent
import com.hieubui.jetflix.movie.details.ui.component.BackButton
import com.hieubui.jetflix.movie.details.ui.component.Backdrop
import com.hieubui.jetflix.movie.details.ui.component.PosterCard
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
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
                this@MovieDetailsFragment.Content(modifier = Modifier.navigationBarsPadding())
            }
        }
    }

    @Composable
    private fun Content(modifier: Modifier = Modifier) {
        val title = "White Elephant"
        val originalTitle = null
        val backdrop = "https://image.tmdb.org/t/p/original/cyV2syN5zRQwU6BmWMyMFyjRLow.jpg"
        val poster = "https://image.tmdb.org/t/p/w342/lG56H40Y1BuHSPoWtEgDJsSgDBZ.jpg"
        val scrollState = rememberScrollState()

        Column(modifier = modifier.verticalScroll(scrollState)) {
            Box(modifier = Modifier.zIndex(zIndex = 2f)) {
                Backdrop(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(256.dp),
                    data = backdrop
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
                    data = poster
                )
            }

            Text(   // Title
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(alignment = CenterHorizontally),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = colors.onSurface,
                text = title
            )

            originalTitle?.let {
                Text(   // Original name
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .align(alignment = CenterHorizontally),
                    fontSize = 14.sp,
                    color = colors.onSurface,
                    text = "($originalTitle)"
                )
            }
        }
    }

    private fun back() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLightStatusBar(false)
    }

    private fun setLightStatusBar(isLightStatusBar: Boolean) {
        val window = requireActivity().window
        val view = requireView()

        getInsetsController(window, view).isAppearanceLightStatusBars = isLightStatusBar
    }

    override fun onDestroyView() {
        setLightStatusBar(true)
        super.onDestroyView()
    }
}