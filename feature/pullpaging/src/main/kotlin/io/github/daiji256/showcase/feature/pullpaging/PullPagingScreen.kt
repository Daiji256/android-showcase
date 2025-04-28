package io.github.daiji256.showcase.feature.pullpaging

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun PullPagingScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_pull_paging_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun PullPagingScreenPreview() {
    // TODO: Theme
    PullPagingScreen(
        onNavigateUpClick = {},
    )
}
