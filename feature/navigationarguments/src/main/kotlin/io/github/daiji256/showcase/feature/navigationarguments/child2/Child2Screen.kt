package io.github.daiji256.showcase.feature.navigationarguments.child2

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun Child2Screen(
    onNavigateUpClick: () -> Unit,
    viewModel: Child2ViewModel = hiltViewModel(),
) {
    Child2Screen(
        argument = viewModel.argument,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Composable
private fun Child2Screen(
    argument: String,
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = "Child2",
        markdown = argument,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun Child2ScreenPreview() {
    // TODO: Theme
    Child2Screen(
        argument = "TODO",
        onNavigateUpClick = {},
    )
}
