package com.hieubui.jetflix.movie.details.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hieubui.jetflix.movie.details.R
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import com.hieubui.jetflix.resources.util.px
import kotlinx.coroutines.Dispatchers

@Composable
internal fun Backdrop(
    modifier: Modifier = Modifier,
    data: String?
) {
    Card(
        modifier = modifier,
        shape = BottomArcShape(arcHeight = 128.dp.px),
        backgroundColor = Color.Gray.copy(alpha = 0.1f),
        elevation = 16.dp
    ) {
        val context = LocalContext.current
        val model = ImageRequest.Builder(context)
            .data(data)
            .dispatcher(Dispatchers.IO)
            .crossfade(500)
            .build()

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentScale = Crop,
            contentDescription = stringResource(id = R.string.backdrop)
        )
    }
}

class BottomArcShape(private val arcHeight: Float) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val arcOffset = arcHeight / 10
            val rect = Rect(
                left = 0f - arcOffset,
                top = size.height - arcHeight,
                right = size.width + arcOffset,
                bottom = size.height
            )

            moveTo(size.width, 0f)
            lineTo(size.width, size.height)
            arcTo(rect, 0f, 180f, false)
            lineTo(0f, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}

@Preview
@Composable
private fun BackdropPreview() {
    JetflixTheme {
        Backdrop(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp),
            data = "https://image.tmdb.org/t/p/original/cyV2syN5zRQwU6BmWMyMFyjRLow.jpg"
        )
    }
}