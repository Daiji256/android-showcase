package io.github.daiji256.showcase.feature.localsnackbarhoststate.first

import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.localsnackbarhoststate.LocalSnackbarHostState
import io.github.daiji256.showcase.core.ui.markdown.Markdown
import kotlinx.coroutines.launch

@Composable
internal fun FirstScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToSecondScreenClick: () -> Unit,
) {
    Document(
        title = "TODO",
        onNavigateUpClick = onNavigateUpClick,
    ) {
        val snackbarHostState = LocalSnackbarHostState.current
        val coroutineScope = rememberCoroutineScope()

        Markdown(
            markdown = "TODO",
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "TODO1",
                        duration = SnackbarDuration.Long,
                    )
                }
            },
        ) {
            Markdown(
                markdown = "TODO1",
            )
        }
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
