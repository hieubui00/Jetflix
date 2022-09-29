package com.hieubui.jetflix.home.ui.component.movie_card

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import kotlinx.coroutines.Dispatchers

@Composable
internal fun MoviePoster(
    modifier: Modifier = Modifier,
    poster: String?
) {
    val context = LocalContext.current
    val model = ImageRequest.Builder(context)
        .data(poster)
        .dispatcher(Dispatchers.IO)
        .crossfade(true)
        .build()

    AsyncImage(
        modifier = modifier,
        model = model,
        contentDescription = "Poster",
        contentScale = ContentScale.FillBounds
    )
}

@Preview
@Composable
private fun MoviePosterPreview() {
    JetflixTheme {
        MoviePoster(poster = "https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/a/v/avatar_2__teaser_poster_1_.jpg")
    }
}