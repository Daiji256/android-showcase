package io.github.daiji256.showcase.feature.navigationarguments.child3

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun Child3Screen(
    onNavigateUpClick: () -> Unit,
    viewModel: Child3ViewModel = hiltViewModel(),
) {
    Child3Screen(
        value = viewModel.value,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Composable
private fun Child3Screen(
    value: String,
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = "Child3",
        markdown = value,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun Child3ScreenPreview() {
    // TODO: Theme
    Child3Screen(
        value = "TODO",
        onNavigateUpClick = {},
    )
}
