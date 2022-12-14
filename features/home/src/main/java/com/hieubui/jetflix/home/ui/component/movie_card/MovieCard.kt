package com.hieubui.jetflix.home.ui.component.movie_card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieubui.jetflix.core.data.model.movie.Movie
import com.hieubui.jetflix.resources.ui.component.Poster
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
internal fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(size = 8.dp),
            elevation = 4.dp
        ) {
            Poster(
                modifier = Modifier.clickable(onClick = onClick),
                data = movie.poster
            )

            MovieInformation(
                modifier = Modifier.align(alignment = Alignment.BottomCenter),
                title = movie.title,
                releaseDate = movie.releaseDate,
                voteCount = movie.voteCount
            )
        }

        RatingLabel(
            modifier = Modifier.align(alignment = Alignment.TopCenter),
            rating = movie.voteAverage
        )
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    JetflixTheme {
        MovieCard(
            movie = Movie(
                movieId = 1,
                title = "Avatar: Dòng Chảy Của Nước",
                releaseDate = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                ).parse("2022-12-14"),
                backdrop = null,
                poster = null,
                voteCount = 5861,
                voteAverage = 7.0f
            ),
            onClick = { }
        )
    }
}
