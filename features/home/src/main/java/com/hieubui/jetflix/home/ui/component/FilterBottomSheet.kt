package com.hieubui.jetflix.home.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ChipDefaults.outlinedFilterChipColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieubui.jetflix.home.R

@Composable
internal fun FilterBottomSheet(
    onClose: () -> Unit,
    onApply: () -> Unit
) {
    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(bottom = 8.dp),
    ) {
        ActionBar(onClose, onApply)

        SortBySection()

        SortOrderSection()
    }
}

@Composable
private fun ActionBar(
    onClose: () -> Unit,
    onApply: () -> Unit
) {
    Surface(elevation = 4.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colors.primary)
                .padding(vertical = 12.dp),
            verticalAlignment = CenterVertically
        ) {
            ActionButton(
                modifier = Modifier.padding(start = 16.dp),
                imageVector = Default.Close,
                contentDescription = stringResource(R.string.close),
                onClick = onClose
            )

            Text( // Title
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp,
                fontWeight = Bold,
                color = colors.onPrimary,
                maxLines = 1,
                overflow = Ellipsis,
                text = stringResource(R.string.sort_and_filter)
            )

            Spacer(modifier = Modifier.weight(weight = 1f))

            ActionButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                imageVector = Default.Refresh,
                contentDescription = stringResource(R.string.reset),
                onClick = { }
            )

            ActionButton(
                modifier = Modifier.padding(end = 16.dp),
                imageVector = Default.Check,
                contentDescription = stringResource(R.string.apply),
                onClick = onApply
            )
        }
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier.size(size = 24.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            tint = colors.onPrimary,
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun SortBySection() {
    val sortByOptions = listOf("Popularity", "Release Date", "Vote Average", "Vote Count")
    var selectedSortByOption by rememberSaveable { mutableStateOf(sortByOptions.first()) }

    SortOptionSection(
        label = "Sort By",
        options = sortByOptions,
        selectedOption = selectedSortByOption,
        onSelectedOptionChange = { selectedSortByOption = it }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SortOptionSection(
    label: String,
    options: List<String>,
    selectedOption: String,
    onSelectedOptionChange: (String) -> Unit
) {
    Text( // Label
        modifier = Modifier.padding(
            top = 8.dp,
            start = 16.dp
        ),
        fontSize = 16.sp,
        fontWeight = Medium,
        text = label
    )

    LazyRow(
        modifier = Modifier.padding(vertical = 0.dp),
        horizontalArrangement = spacedBy(space = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(items = options) { index, option ->
            FilterChip(
                selected = options[index] == selectedOption,
                border = BorderStroke(
                    width = 1.dp,
                    color = colors.primary
                ),
                colors = outlinedFilterChipColors(
                    backgroundColor = Transparent,
                    contentColor = colors.onBackground,
                    selectedBackgroundColor = colors.primary,
                    selectedContentColor = colors.onPrimary
                ),
                selectedIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(size = 18.dp),
                        imageVector = Default.Check,
                        contentDescription = "Check"
                    )
                },
                onClick = { onSelectedOptionChange(options[index]) }
            ) {
                Text(
                    fontSize = 14.sp,
                    text = option
                )
            }
        }
    }
}

@Composable
private fun SortOrderSection() {
    val sortOrderOptions = listOf("Descending", "Ascending")
    var selectedSortOrderOption by rememberSaveable { mutableStateOf(sortOrderOptions[0]) }

    SortOptionSection(
        label = "Sort Order",
        options = sortOrderOptions,
        selectedOption = selectedSortOrderOption,
        onSelectedOptionChange = { selectedSortOrderOption = it }
    )
}