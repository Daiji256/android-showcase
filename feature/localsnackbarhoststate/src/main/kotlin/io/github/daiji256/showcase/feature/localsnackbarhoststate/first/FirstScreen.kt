package io.github.daiji256.showcase.feature.localsnackbarhoststate.first

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.markdown.Markdown

@Composable
internal fun FirstScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToSecondScreenClick: () -> Unit,
) {
    Document(
        title = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    ) {
        Markdown(
            markdown = "TODO",
        )
        Button(
            onClick = dropUnlessResumed { onNavigateToSecondScreenClick() },
        ) {
            Markdown(
                markdown = "TODO",
            )
        }
    }
}

@Preview
@Composable
private fun FirstScreenPreview() {
    // TODO: Theme
    FirstScreen(
        onNavigateUpClick = {},
        onNavigateToSecondScreenClick = {},
    )
}
