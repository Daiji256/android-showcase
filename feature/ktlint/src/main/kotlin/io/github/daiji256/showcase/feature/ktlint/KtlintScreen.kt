package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun KtlintScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_ktlint_title),
        markdown = stringResource(id = R.string.feature_ktlint_document_md),
        onNavigateUpClick = onNavigateUpClick,
    )
}
