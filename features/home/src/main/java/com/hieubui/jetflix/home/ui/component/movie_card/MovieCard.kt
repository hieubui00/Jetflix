package com.hieubui.jetflix.home.ui.component.movie_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
internal fun MovieCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        val rating = 7.0f

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(size = 8.dp),
            elevation = 8.dp
        ) {
            val title = "Avatar: Dòng Chảy Của Nước"
            val releaseDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2022-12-14")
            val voteCount = 5861
            val poster = "https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/a/v/avatar_2__teaser_poster_1_.jpg"

            MoviePoster(
                modifier = Modifier.clickable { },
                poster = poster
            )

            MovieInformation(
                modifier = Modifier
                    .align(alignment = Alignment.BottomStart)
                    .background(Color(0x66000000))
                    .padding(vertical = 4.dp),
                title = title,
                releaseDate = releaseDate,
                voteCount = voteCount
            )
        }

        MovieRating(
            modifier = Modifier.align(alignment = Alignment.TopCenter),
            rating = rating
        )
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    JetflixTheme {
        MovieCard()
    }
}