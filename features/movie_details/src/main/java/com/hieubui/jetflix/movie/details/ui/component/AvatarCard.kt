package com.hieubui.jetflix.movie.details.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.hieubui.jetflix.movie.details.R
import kotlinx.coroutines.Dispatchers

@Composable
internal fun AvatarCard(
    modifier: Modifier = Modifier,
    avatar: Any?
) {
    val context = LocalContext.current
    val model = ImageRequest.Builder(context)
        .data(avatar)
        .dispatcher(Dispatchers.IO)
        .transformations(CircleCropTransformation())
        .crossfade(300)
        .build()

    Card(
        modifier = modifier,
        shape = CircleShape,
        elevation = 16.dp
    ) {
        AsyncImage(
            model = model,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.img_avatar_default),
            contentDescription = stringResource(R.string.avatar)
        )
    }
}
