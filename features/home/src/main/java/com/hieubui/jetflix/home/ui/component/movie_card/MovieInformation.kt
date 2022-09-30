package com.hieubui.jetflix.home.ui.component.movie_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieubui.jetflix.home.R
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
internal fun MovieInformation(
    modifier: Modifier = Modifier,
    title: String?,
    releaseDate: Date?,
    voteCount: Int?
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        Text(   // Title
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = title.orEmpty()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(14.dp),
                imageVector = Icons.Default.DateRange,
                contentDescription = stringResource(id = R.string.calendar),
                tint = Color.White
            )

            Text(   // Release Date
                modifier = Modifier.padding(horizontal = 4.dp),
                fontSize = 12.sp,
                color = Color.White,
                maxLines = 1,
                text = releaseDate?.let { simpleDateFormat.format(it) }.orEmpty()
            )

            Spacer(modifier = Modifier.weight(weight = 1f))

            Icon(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(14.dp),
                imageVector = Icons.Default.ThumbUp,
                contentDescription = stringResource(id = R.string.vote),
                tint = Color.White
            )

            Text(   // Vote count
                modifier = Modifier.padding(end = 8.dp),
                fontSize = 12.sp,
                color = Color.White,
                maxLines = 1,
                text = voteCount?.toString().orEmpty()
            )
        }
    }
}

@Preview
@Composable
private fun MovieInformationPreview() {
    JetflixTheme {
        MovieInformation(
            modifier = Modifier
                .background(Color(0x66000000))
                .padding(vertical = 4.dp),
            title = "Avatar: Dòng Chảy Của Nước",
            releaseDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2022-12-14"),
            voteCount = 5861
        )
    }
}