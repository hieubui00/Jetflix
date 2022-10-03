package com.hieubui.jetflix.movie.details.ui.component

import androidx.compose.foundation.layout.Arrangement.Horizontal
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hieubui.jetflix.resources.ui.component.StarHalf
import com.hieubui.jetflix.resources.ui.component.StarOutline
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
internal fun RatingBar(
    modifier: Modifier = Modifier,
    horizontalArrangement: Horizontal = Start,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    numStars: Int = 5,
    max: Float,
    rating: Float
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement
    ) {
        val average = max / numStars
        val count = rating / average

        repeat(numStars) { index ->
            val imageVector = when {
                count >= index + 1 -> Filled.Star

                count in index.toFloat()..(index + 1).toFloat() -> Filled.StarHalf

                else -> Filled.StarOutline
            }

            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = tint
            )
        }
    }
}

@Preview
@Composable
private fun RatingBarPreview() {
    JetflixTheme {
        RatingBar(
            max = 10.0f,
            rating = 6.5f
        )
    }
}
