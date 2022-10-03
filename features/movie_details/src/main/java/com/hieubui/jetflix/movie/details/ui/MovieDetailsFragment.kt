package com.hieubui.jetflix.movie.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat.getInsetsController
import androidx.fragment.app.Fragment
import com.hieubui.jetflix.movie.details.ui.component.BackButton
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

class MovieDetailsFragment : Fragment() {

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
        val scrollState = rememberScrollState()

        Column(modifier = modifier.verticalScroll(scrollState)) {
            Box {
                BackButton(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(all = 16.dp)
                        .size(size = 24.dp),
                    onClick = this@MovieDetailsFragment::back
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