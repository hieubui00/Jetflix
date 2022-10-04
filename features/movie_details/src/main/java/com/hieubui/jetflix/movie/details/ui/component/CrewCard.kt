package com.hieubui.jetflix.movie.details.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieubui.jetflix.core.data.model.movie.credits.Crew
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
internal fun CrewCard(
    modifier: Modifier = Modifier,
    crew: Crew?
) {
    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally
    ) {
        AvatarCard(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .aspectRatio(ratio = 1f),
            avatar = crew?.avatar
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            fontSize = 16.sp,
            fontWeight = Bold,
            color = colors.onSurface,
            textAlign = Center,
            maxLines = 1,
            overflow = Ellipsis,
            text = crew?.name.orEmpty()
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            fontSize = 14.sp,
            fontStyle = Italic,
            color = colors.onSurface,
            textAlign = Center,
            maxLines = 1,
            overflow = Ellipsis,
            text = crew?.job.orEmpty()
        )
    }
}

@Preview
@Composable
private fun CrewCardPreview() {
    JetflixTheme {
        CrewCard(
            crew = Crew(
                crewId = 1,
                name = "Virginia Gardner",
                originalName = "Virginia Gardner",
                avatar = "https://image.tmdb.org/t/p/original/1DnNysK267b0te48KCkUlTKoTzj.jpg",
                job = "Director"
            )
        )
    }
}
