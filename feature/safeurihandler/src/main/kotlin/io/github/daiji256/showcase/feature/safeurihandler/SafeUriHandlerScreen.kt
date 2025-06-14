package io.github.daiji256.showcase.feature.safeurihandler

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun SafeUriHandlerScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_safe_uri_handler_title),
        markdown = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun SafeUriHandlerScreenPreview() {
    // TODO: Theme
    SafeUriHandlerScreen(
        onNavigateUpClick = {},
    )
}
