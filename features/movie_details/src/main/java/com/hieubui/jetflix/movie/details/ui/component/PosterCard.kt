package com.hieubui.jetflix.movie.details.ui.component

import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hieubui.jetflix.resources.ui.component.Poster
import com.hieubui.jetflix.resources.ui.theme.JetflixTheme

@Composable
internal fun PosterCard(
    modifier: Modifier = Modifier,
    data: Any?
) {
    var isScaled by rememberSaveable { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isScaled) 2.0f else 1.0f,
        animationSpec = spring(
            dampingRatio = DampingRatioMediumBouncy,
            stiffness = StiffnessLow,
            visibilityThreshold = 0.001f
        )
    )

    Card(
        modifier = modifier
            .scale(scale)
            .clickable { isScaled = !isScaled },
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp
    ) {
        Poster(data = data)
    }
}

@Preview
@Composable
private fun PosterCardPreview() {
    JetflixTheme {
        PosterCard(
            modifier = Modifier.width(160.dp),
            data = null
        )
    }
}
