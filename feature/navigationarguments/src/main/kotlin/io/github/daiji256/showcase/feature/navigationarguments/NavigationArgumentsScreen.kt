package io.github.daiji256.showcase.feature.navigationarguments

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun NavigationArgumentsScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_navigation_arguments_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun NavigationArgumentsScreenPreview() {
    // TODO: Theme
    NavigationArgumentsScreen(
        onNavigateUpClick = {},
    )
}
