package io.github.daiji256.showcase.feature.navigationarguments.viewmodelargsreceiver

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun ViewModelArgsReceiverScreen(
    onNavigateUpClick: () -> Unit,
    viewModel: ViewModelArgsReceiverViewModel = hiltViewModel(),
) {
    ViewModelArgsReceiverScreen(
        arg = viewModel.arg,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Composable
private fun ViewModelArgsReceiverScreen(
    arg: String,
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = "ViewModelArgsReceiver",
        markdown = arg,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun ViewModelArgsReceiverScreenPreview() {
    // TODO: Theme
    ViewModelArgsReceiverScreen(
        arg = "TODO",
        onNavigateUpClick = {},
    )
}
