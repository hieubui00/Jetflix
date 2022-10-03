package com.hieubui.jetflix.movie.details.ui.component

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hieubui.jetflix.core.data.model.ProductionCompany
import com.hieubui.jetflix.movie.details.R
import kotlinx.coroutines.Dispatchers

@Composable
internal fun ProductionCompanyCard(
    modifier: Modifier = Modifier,
    productionCompany: ProductionCompany
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = 8.dp),
        backgroundColor = White,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
            verticalArrangement = spacedBy(space = 8.dp)
        ) {
            val context = LocalContext.current
            val model = ImageRequest.Builder(context)
                .data(productionCompany.logo)
                .dispatcher(Dispatchers.IO)
                .crossfade(true)
                .build()

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f),
                model = model,
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(R.string.logo)
            )

            Text(
                modifier = Modifier.align(alignment = CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Black,
                maxLines = 1,
                overflow = Ellipsis,
                text = productionCompany.name.orEmpty()
            )
        }
    }
}
