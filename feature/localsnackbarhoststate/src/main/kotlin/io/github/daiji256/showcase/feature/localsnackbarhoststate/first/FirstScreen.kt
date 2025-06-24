package io.github.daiji256.showcase.feature.localsnackbarhoststate.first

import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.document.Document
import io.github.daiji256.showcase.core.ui.localsnackbarhoststate.LocalSnackbarHostState
import io.github.daiji256.showcase.core.ui.markdown.Markdown
import io.github.daiji256.showcase.feature.localsnackbarhoststate.R
import kotlinx.coroutines.launch

@Composable
internal fun FirstScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToSecondScreenClick: () -> Unit,
) {
    Document(
        title = stringResource(id = R.string.feature_lshs_first_title),
        onNavigateUpClick = onNavigateUpClick,
    ) {
        val snackbarHostState = LocalSnackbarHostState.current
        val coroutineScope = rememberCoroutineScope()
        val snackbarMessage = stringResource(id = R.string.feature_lshs_first_snackbar_message)

        Button(
            onClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        duration = SnackbarDuration.Long,
                    )
                }
            },
        ) {
            Text(
                text = stringResource(
                    id = R.string.feature_lshs_first_document_show_snackbar_button,
                ),
            )
        }
        Button(
            onClick = dropUnlessResumed { onNavigateToSecondScreenClick() },
        ) {
            Text(
                text = stringResource(
                    id = R.string.feature_lshs_first_navigate_to_second_screen_button,
                ),
            )
        }

        HorizontalDivider()

        Markdown(
            markdown = stringResource(
                id = R.string.feature_lshs_first_document_summary_md,
            ),
        )
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
