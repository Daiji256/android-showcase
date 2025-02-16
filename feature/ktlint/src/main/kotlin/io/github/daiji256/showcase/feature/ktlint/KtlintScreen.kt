package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController

@Composable
internal fun KtlintScreen() {
    val navController = LocalNavController.current
    KtlintScreen(
        onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
    )
}

@Composable
private fun KtlintScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_ktlint_title),
        markdown = stringResource(id = R.string.feature_ktlint_document_md),
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun KtlintScreenPreview() {
    // TODO: Theme
    KtlintScreen(
        onNavigateUpClick = {},
    )
}
