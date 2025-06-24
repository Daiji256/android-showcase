package io.github.daiji256.showcase.feature.localsnackbarhoststate.second

import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.localsnackbarhoststate.LocalSnackbarHostState
import io.github.daiji256.showcase.core.ui.markdown.Markdown
import kotlinx.coroutines.launch

@Composable
internal fun SecondScreen(
    onNavigateUpClick: () -> Unit,
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
                        message = "TODO2",
                        duration = SnackbarDuration.Long,
                    )
                }
            },
        ) {
            Markdown(
                markdown = "TODO2",
            )
        }
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
