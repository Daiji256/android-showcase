package io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun DiArgsReceiverScreen(
    onNavigateUpClick: () -> Unit,
    viewModel: DiArgsReceiverViewModel = hiltViewModel(),
) {
    DiArgsReceiverScreen(
        arg = viewModel.arg,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Composable
private fun DiArgsReceiverScreen(
    arg: String,
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = "DiArgsReceiver",
        markdown = arg,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun DiArgsReceiverScreenPreview() {
    // TODO: Theme
    DiArgsReceiverScreen(
        arg = "TODO",
        onNavigateUpClick = {},
    )
}
