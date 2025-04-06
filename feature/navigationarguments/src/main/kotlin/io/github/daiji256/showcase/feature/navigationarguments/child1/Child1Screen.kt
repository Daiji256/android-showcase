package io.github.daiji256.showcase.feature.navigationarguments.child1

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document

@Composable
internal fun Child1Screen(
    value: String,
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = "Child1",
        markdown = value,
        onNavigateUpClick = onNavigateUpClick,
    )
}

@Preview
@Composable
private fun Child1ScreenPreview() {
    // TODO: Theme
    Child1Screen(
        value = "TODO",
        onNavigateUpClick = {},
    )
}
