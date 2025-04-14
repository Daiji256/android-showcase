package io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun DirectArgsReceiverScreen(
    arg: String,
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = "DirectArgsReceiver",
        markdown = arg,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun DirectArgsReceiverScreenPreview() {
    // TODO: Theme
    DirectArgsReceiverScreen(
        arg = "TODO",
        onNavigateUpClick = {},
    )
}
