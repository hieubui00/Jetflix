package com.hieubui.jetflix.home.ui.component.movie_card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
internal fun RatingLabel(
    modifier: Modifier = Modifier,
    rating: Float?
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(size = 16.dp),
        color = White,
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Black,
            text = (rating ?: 0.0f).toString()
        )
    }
}

@Preview
@Composable
private fun RatingLabelPreview() {
    JetflixTheme {
        RatingLabel(rating = 7.0f)
    }
}
