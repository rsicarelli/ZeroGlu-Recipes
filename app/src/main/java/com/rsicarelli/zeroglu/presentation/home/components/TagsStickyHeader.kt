package com.rsicarelli.zeroglu.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rsicarelli.zeroglu.presentation.home.TagItem
import com.rsicarelli.zeroglu.ui.ComposeLazyList

@Composable
internal fun TagsStickyHeader(
    modifier: Modifier = Modifier,
    tags: ComposeLazyList<TagItem>,
    selectedTags: Sequence<TagItem>,
    onTagSelected: (TagItem) -> Unit,
) {
    ChipGroup(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        items = tags,
        selectedItems = selectedTags,
        onSelectedChanged = onTagSelected,
        chipName = TagItem::description
    )
}