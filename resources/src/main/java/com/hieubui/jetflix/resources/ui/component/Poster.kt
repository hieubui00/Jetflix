package com.hieubui.jetflix.resources.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.hieubui.jetflix.resources.R
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import kotlinx.coroutines.Dispatchers

@Composable
fun Poster(
    modifier: Modifier = Modifier,
    data: Any?
) {
    val context = LocalContext.current
    val model = ImageRequest.Builder(context)
        .data(data)
        .dispatcher(Dispatchers.IO)
        .crossfade(true)
        .build()

    SubcomposeAsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f / 3f),
        model = model,
        contentDescription = stringResource(id = R.string.poster),
        contentScale = Crop,
        loading = {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp)
                        .align(alignment = Center)
                )
            }
        }
    )
}

@Preview
@Composable
private fun MoviePosterPreview() {
    JetflixTheme {
        Poster(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f),
            data = "https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/a/v/avatar_2__teaser_poster_1_.jpg"
        )
    }
}
