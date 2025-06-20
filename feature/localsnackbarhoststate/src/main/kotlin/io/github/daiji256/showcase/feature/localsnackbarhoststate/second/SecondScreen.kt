package io.github.daiji256.showcase.feature.localsnackbarhoststate.second

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown

@Composable
internal fun SecondScreen(
    onNavigateUpClick: () -> Unit,
) {
    Document(
        title = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Markdown(
            markdown = "TODO",
        )
    }
}

@Preview
@Composable
private fun SecondScreenPreview() {
    // TODO: Theme
    SecondScreen(
        onNavigateUpClick = {},
    )
}
