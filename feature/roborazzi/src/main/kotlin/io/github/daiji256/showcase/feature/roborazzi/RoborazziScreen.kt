package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController

@Composable
internal fun RoborazziScreen() {
    val navController = LocalNavController.current
    RoborazziScreen(
        onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
    )
}

@Composable
private fun RoborazziScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_roborazzi_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun RoborazziScreenPreview() {
    // TODO: Theme
    RoborazziScreen(
        onNavigateUpClick = {},
    )
}
