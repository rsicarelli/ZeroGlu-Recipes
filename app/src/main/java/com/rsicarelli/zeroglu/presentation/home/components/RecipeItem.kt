package com.rsicarelli.zeroglu.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rsicarelli.zeroglu.app.ui.defaultPlaceholder
import com.rsicarelli.zeroglu.presentation.home.RecipeItem
import com.rsicarelli.zeroglu.presentation.home.TagItem
import com.rsicarelli.zeroglu.presentation.home.components.RecipeItemDefaults.CardShape
import com.rsicarelli.zeroglu.presentation.home.components.RecipeItemDefaults.DefaultArrangement
import com.rsicarelli.zeroglu.presentation.home.components.RecipeItemDefaults.HorizontalPadding
import com.rsicarelli.zeroglu.presentation.home.components.RecipeItemDefaults.VerticalPadding

@Composable
internal fun RecipeItem(
    modifier: Modifier = Modifier,
    recipe: RecipeItem,
    onNavigateToDetail: () -> Unit,
    isLoading: Boolean,
) = RecipeItemContent(
    modifier = modifier,
    isLoading = isLoading,
    onNavigateToDetail = onNavigateToDetail,
    recipe = recipe
)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun RecipeItemContent(
    modifier: Modifier = Modifier,
    onNavigateToDetail: () -> Unit,
    recipe: RecipeItem,
    isLoading: Boolean,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onNavigateToDetail,
        shape = RoundedCornerShape(CardShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HorizontalPadding)
                .then(Modifier.padding(vertical = VerticalPadding)),
            verticalArrangement = DefaultArrangement
        ) {

            Text(
                modifier = Modifier.defaultPlaceholder(isLoading),
                text = recipe.title.trim(),
                style = MaterialTheme.typography.headlineSmall,
            )

            LazyRow(
                horizontalArrangement = DefaultArrangement
            ) {
                items(
                    items = recipe.tags,
                    key = TagItem::id
                ) { tagItem ->
                    RecipeItemChip(
                        isLoading = isLoading,
                        tagName = tagItem.description
                    )
                }
            }
        }
    }
}

@Composable
private fun RecipeItemChip(
    modifier: Modifier = Modifier,
    verticalPadding: Dp = 8.dp,
    horizontalPadding: Dp = 12.dp,
    tagName: String,
    isLoading: Boolean,
    borderStroke: BorderStroke = BorderStroke(if (isLoading) 0.dp else 1.dp, colorScheme.onSurface.copy(alpha = 0.15F)),
) {
    Box(
        modifier = modifier
            .border(borderStroke, MaterialTheme.shapes.extraLarge)
            .defaultPlaceholder(isLoading),
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .then(Modifier.padding(vertical = verticalPadding)),
            text = tagName,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

private object RecipeItemDefaults {

    @Stable
    val CardShape = 24.dp

    @Stable
    val HorizontalPadding = 24.dp

    @Stable
    val VerticalPadding = 24.dp

    @Stable
    val DefaultArrangement = Arrangement.spacedBy(8.dp)
}
