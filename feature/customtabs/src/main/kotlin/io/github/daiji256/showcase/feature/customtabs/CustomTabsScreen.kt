package io.github.daiji256.showcase.feature.customtabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController

@Composable
internal fun CustomTabsScreen() {
    val navController = LocalNavController.current
    CustomTabsScreen(
        onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
    )
}

@Composable
private fun CustomTabsScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_custom_tabs_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun CustomTabsScreenPreview() {
    // TODO: Theme
    CustomTabsScreen(
        onNavigateUpClick = {},
    )
}
